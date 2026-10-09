package com.movieapp.movie_backend.review;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Integer> {
    Optional<Review> findByUserIdAndTmdbIdAndMediaType(
            Integer userId,
            Integer tmdbId,
            String mediaType
    );
    Optional<Review> findByIdAndUserId(
            Integer id,
            Integer userId
    );

    List<Review> findByTmdbIdAndMediaType(
            Integer tmdbId,
            String mediaType
    );


}
