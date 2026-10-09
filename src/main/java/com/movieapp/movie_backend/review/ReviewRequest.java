package com.movieapp.movie_backend.review;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class ReviewRequest {
    private Integer tmdbId;

    private String mediaType;

    @NotBlank
    private String content;

    private Boolean spoiler;
}
