package com.movieapp.movie_backend.review;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReviewResponse {
    private Integer id;

    private Integer tmdbId;

    private String mediaType;

    private String username;

    private String content;

    private Boolean spoiler;
}
