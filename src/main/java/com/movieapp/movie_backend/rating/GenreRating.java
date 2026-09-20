package com.movieapp.movie_backend.rating;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "genre_ratings")
public class GenreRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "rating_id")
    private Integer ratingId;

    private Integer genreId;

    private String genreName;

    private Integer score;

}
