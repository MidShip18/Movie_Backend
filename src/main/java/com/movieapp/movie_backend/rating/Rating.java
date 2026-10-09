package com.movieapp.movie_backend.rating;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ratings")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer userId;
    private Integer tmdbId;
    private String mediaType;
    private Integer feelScore;
    private Integer storyScore;
    private Integer impactScore;
    private Boolean rewatchable;
}
