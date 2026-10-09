package com.movieapp.movie_backend.rating;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RatingSummaryResponse {
    private Integer tmdbId;
    private String mediaType;

    private Long totalRatings;

    private Double averageFeelScore;
    private Double averageStoryScore;
    private Double averageImpactScore;

    private Double overallRating;
    private List<GenreRatingSummaryResponse> genreRatings;

    private Long rewatchableCount;
    private Double rewatchablePercentage;
}
