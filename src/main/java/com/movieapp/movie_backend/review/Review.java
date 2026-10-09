package com.movieapp.movie_backend.review;

import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "reviews")
@Getter
@Setter
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name= "user_id")
    private Integer userId;

    @Column(name = "tmdb_id")
    private Integer tmdbId;

    @Column(name = "media_type")
    private String mediaType;

    @Column(name = "content")
    private String content;

    @Column(name = "spoiler")
    private Boolean spoiler;
}

