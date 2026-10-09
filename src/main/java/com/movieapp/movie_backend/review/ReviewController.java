package com.movieapp.movie_backend.review;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ReviewResponse saveReview(
            @Valid @RequestBody ReviewRequest request) {

        return reviewService.saveReview(request);
    }
    @GetMapping("/movie/{tmdbId}")
    public List<ReviewResponse> getReviews(
            @PathVariable Integer tmdbId,
            @RequestParam String mediaType) {

        return reviewService.getReviews(tmdbId, mediaType);
    }
    @PutMapping("/{id}")
    public ReviewResponse updateReview(
            @PathVariable Integer id,
            @Valid @RequestBody ReviewRequest request) {

        return reviewService.updateReview(id, request);
    }
    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable Integer id) {
        reviewService.deleteReview(id);
    }
}
