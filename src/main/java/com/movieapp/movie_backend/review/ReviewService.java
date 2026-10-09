package com.movieapp.movie_backend.review;

import org.springframework.stereotype.Service;
import com.movieapp.movie_backend.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import com.movieapp.movie_backend.entity.User;
import java.util.ArrayList;
import java.util.List;


@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    public ReviewService(ReviewRepository reviewRepository,
                         UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
    }
    public ReviewResponse saveReview(ReviewRequest request) {

        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Review review = reviewRepository
                .findByUserIdAndTmdbIdAndMediaType(
                        user.getId(),
                        request.getTmdbId(),
                        request.getMediaType()
                )
                .orElse(new Review());

        review.setUserId(user.getId());
        review.setTmdbId(request.getTmdbId());
        review.setMediaType(request.getMediaType());
        review.setContent(request.getContent());
        review.setSpoiler(request.getSpoiler());

        review = reviewRepository.save(review);

        ReviewResponse response = new ReviewResponse();

        response.setId(review.getId());
        response.setTmdbId(review.getTmdbId());
        response.setMediaType(review.getMediaType());
        response.setUsername(user.getUsername());
        response.setContent(review.getContent());
        response.setSpoiler(review.getSpoiler());

        return response;
    }
    public List<ReviewResponse> getReviews(Integer tmdbId, String mediaType) {

        List<Review> reviews =
                reviewRepository.findByTmdbIdAndMediaType(tmdbId, mediaType);

        List<ReviewResponse> responses = new ArrayList<>();

        for (Review review : reviews) {

            User user = userRepository.findById(review.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            ReviewResponse response = new ReviewResponse();

            response.setId(review.getId());
            response.setTmdbId(review.getTmdbId());
            response.setMediaType(review.getMediaType());
            response.setUsername(user.getUsername());
            response.setContent(review.getContent());
            response.setSpoiler(review.getSpoiler());

            responses.add(response);
        }

        return responses;
    }
    public ReviewResponse updateReview(Integer reviewId, ReviewRequest request) {

        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Review review = reviewRepository
                .findByIdAndUserId(reviewId, user.getId())
                .orElseThrow(() -> new RuntimeException("Review not found"));

        review.setContent(request.getContent());
        review.setSpoiler(request.getSpoiler());

        review = reviewRepository.save(review);

        ReviewResponse response = new ReviewResponse();

        response.setId(review.getId());
        response.setTmdbId(review.getTmdbId());
        response.setMediaType(review.getMediaType());
        response.setUsername(user.getUsername());
        response.setContent(review.getContent());
        response.setSpoiler(review.getSpoiler());

        return response;
    }
    public void deleteReview(Integer reviewId) {

        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Review review = reviewRepository
                .findByIdAndUserId(reviewId, user.getId())
                .orElseThrow(() -> new RuntimeException("Review not found"));

        reviewRepository.delete(review);
    }
}
