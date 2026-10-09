package com.movieapp.movie_backend.rating;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RatingRequest {
    private List<GenreRatingRequest> genreRatings;
    private Integer tmdbId;

    private String mediaType;

    @Min(1)
    @Max(5)
    private Integer feelScore;

    @Min(1)
    @Max(5)
    private Integer storyScore;

    @Min(1)
    @Max(5)
    private Integer impactScore;

    private Boolean rewatchable;

}
