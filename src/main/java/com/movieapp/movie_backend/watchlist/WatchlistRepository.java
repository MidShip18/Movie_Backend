package com.movieapp.movie_backend.watchlist;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface WatchlistRepository extends JpaRepository<Watchlist,Integer> {

    Optional<Watchlist> findByUserIdAndTmdbIdAndMediaType(
            Integer userId,
            Integer tmdbId,
            String mediaType
    );
    List<Watchlist> findByUserId(Integer userId);
}
