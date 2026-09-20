package com.movieapp.movie_backend.rating;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Getter
@Setter
@NoArgsConstructor
public class GenreRatingRequest {
    private Integer genreId;

    private String genreName;

    @Min(1)
    @Max(5)
    private Integer score;
}
