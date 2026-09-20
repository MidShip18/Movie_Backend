package com.movieapp.movie_backend.rating;

import org.springframework.stereotype.Service;
import com.movieapp.movie_backend.entity.User;
import com.movieapp.movie_backend.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import java.util.Optional;
import com.movieapp.movie_backend.tmdb.TmdbClient;
import com.movieapp.movie_backend.tmdb.dto.DetailsDto;
import com.movieapp.movie_backend.tmdb.dto.GenreDto;
import java.util.List;
import java.util.ArrayList;

@Service
public class RatingService {
    private final RatingRepository ratingRepository;
    private final UserRepository userRepository;
    private final GenreRatingRepository genreRatingRepository;
    private final TmdbClient tmdbClient;
    public RatingService(
            RatingRepository ratingRepository,
            UserRepository userRepository,
            GenreRatingRepository genreRatingRepository, TmdbClient tmdbClient) {

        this.ratingRepository = ratingRepository;
        this.userRepository = userRepository;
        this.genreRatingRepository = genreRatingRepository;
        this.tmdbClient = tmdbClient;
    }
    public RatingResponse saveRating(RatingRequest request) {
        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Optional<Rating> existingRating =
                ratingRepository.findByUserIdAndTmdbIdAndMediaType(
                        user.getId(),
                        request.getTmdbId(),
                        request.getMediaType()
                );

        Rating rating;

        if (existingRating.isPresent()) {
            rating = existingRating.get();

            genreRatingRepository.deleteByRatingId(rating.getId());
        } else {
            rating = new Rating();
        }

        rating.setUserId(user.getId());
        rating.setTmdbId(request.getTmdbId());
        rating.setMediaType(request.getMediaType());
        rating.setFeelScore(request.getFeelScore());
        rating.setStoryScore(request.getStoryScore());
        rating.setImpactScore(request.getImpactScore());

        Rating savedRating = ratingRepository.save(rating);
        DetailsDto details = tmdbClient.getDetails(
                request.getMediaType(),
                request.getTmdbId()
        );
        if (request.getGenreRatings() != null) {

            for (GenreRatingRequest genreRequest : request.getGenreRatings()) {

                GenreDto matchedGenre = details.getGenres().stream()
                        .filter(genre -> genre.getId().equals(genreRequest.getGenreId()))
                        .findFirst()
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Invalid genre for this movie/series"
                                )
                        );

                GenreRating genreRating = new GenreRating();

                genreRating.setRatingId(savedRating.getId());
                genreRating.setGenreId(matchedGenre.getId());
                genreRating.setGenreName(matchedGenre.getName());
                genreRating.setScore(genreRequest.getScore());

                genreRatingRepository.save(genreRating);
            }
        }
        RatingResponse response = new RatingResponse();

        response.setId(savedRating.getId());
        response.setTmdbId(savedRating.getTmdbId());
        response.setMediaType(savedRating.getMediaType());
        response.setFeelScore(savedRating.getFeelScore());
        response.setStoryScore(savedRating.getStoryScore());
        response.setImpactScore(savedRating.getImpactScore());

        return response;
    }
    public UserRatingResponse getUserRating(Integer tmdbId, String mediaType) {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Rating rating = ratingRepository
                .findByUserIdAndTmdbIdAndMediaType(
                        user.getId(),
                        tmdbId,
                        mediaType
                )
                .orElseThrow(() ->
                        new RuntimeException("Rating not found")
                );

        List<GenreRating> genreRatings =
                genreRatingRepository.findByRatingId(rating.getId());

        UserRatingResponse response = new UserRatingResponse();

        response.setId(rating.getId());
        response.setTmdbId(rating.getTmdbId());
        response.setMediaType(rating.getMediaType());
        response.setFeelScore(rating.getFeelScore());
        response.setStoryScore(rating.getStoryScore());
        response.setImpactScore(rating.getImpactScore());

        List<GenreRatingResponse> genreResponses = new ArrayList<>();

        for (GenreRating genreRating : genreRatings) {

            GenreRatingResponse genreResponse =
                    new GenreRatingResponse();

            genreResponse.setGenreId(genreRating.getGenreId());
            genreResponse.setGenreName(genreRating.getGenreName());
            genreResponse.setScore(genreRating.getScore());

            genreResponses.add(genreResponse);
        }

        response.setGenreRatings(genreResponses);

        return response;
    }
    public RatingSummaryResponse getRatingSummary(
            Integer tmdbId,
            String mediaType) {

        Double averageFeel =
                ratingRepository.getAverageFeelScore(tmdbId, mediaType);

        Double averageStory =
                ratingRepository.getAverageStoryScore(tmdbId, mediaType);

        Double averageImpact =
                ratingRepository.getAverageImpactScore(tmdbId, mediaType);

        Long totalRatings =
                ratingRepository.getTotalRatings(tmdbId, mediaType);
        List<Object[]> genreAverages =
                genreRatingRepository.getGenreAverages(tmdbId, mediaType);

        List<GenreRatingSummaryResponse> genreResponses =
                new ArrayList<>();

        for (Object[] row : genreAverages) {

            GenreRatingSummaryResponse genreResponse =
                    new GenreRatingSummaryResponse();

            genreResponse.setGenreId((Integer) row[0]);
            genreResponse.setGenreName((String) row[1]);
            genreResponse.setAverageScore(
                    ((Number) row[2]).doubleValue()
            );

            genreResponses.add(genreResponse);
        }
        if (totalRatings == 0) {
            RatingSummaryResponse response = new RatingSummaryResponse();

            response.setTmdbId(tmdbId);
            response.setMediaType(mediaType);
            response.setTotalRatings(0L);

            return response;
        }

        Double overallRating =
                (averageFeel + averageStory + averageImpact) / 3;

        RatingSummaryResponse response = new RatingSummaryResponse();

        response.setTmdbId(tmdbId);
        response.setMediaType(mediaType);
        response.setTotalRatings(totalRatings);
        response.setAverageFeelScore(averageFeel);
        response.setAverageStoryScore(averageStory);
        response.setAverageImpactScore(averageImpact);
        response.setOverallRating(overallRating);
        response.setGenreRatings(genreResponses);

        return response;
    }
}
