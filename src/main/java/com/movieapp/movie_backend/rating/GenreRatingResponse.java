package com.movieapp.movie_backend.rating;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GenreRatingResponse {
    private Integer genreId;
    private String genreName;
    private Integer score;
}

