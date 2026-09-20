package com.movieapp.movie_backend.rating;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {
    private final RatingService ratingService;
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping
    public RatingResponse saveRating(@Valid @RequestBody RatingRequest request){
        return ratingService.saveRating(request);
    }
    @GetMapping("/movie/{tmdbId}")
    public UserRatingResponse getUserRating(
            @PathVariable Integer tmdbId,
            @RequestParam String mediaType) {

        return ratingService.getUserRating(tmdbId, mediaType);
    }
    @GetMapping("/movie/{tmdbId}/summary")
    public RatingSummaryResponse getRatingSummary(
            @PathVariable Integer tmdbId,
            @RequestParam String mediaType) {

        return ratingService.getRatingSummary(tmdbId, mediaType);
    }
}
