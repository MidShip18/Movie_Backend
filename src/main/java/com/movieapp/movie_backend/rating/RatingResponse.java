package com.movieapp.movie_backend.rating;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RatingResponse {
    private Integer id;
    private Integer tmdbId;
    private String mediaType;
    private Integer feelScore;
    private Integer storyScore;
    private Integer impactScore;
    private Boolean rewatchable;
}
