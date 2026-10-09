package com.movieapp.movie_backend.watchlist;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "watchlist")
@Getter
@Setter
@NoArgsConstructor

public class Watchlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "tmdb_id")
    private Integer tmdbId;

    @Column(name = "media_type")
    private String mediaType;

    @Column(name = "added_at")
    private LocalDateTime addedAt;
}
