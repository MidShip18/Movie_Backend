package com.movieapp.movie_backend.rating;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RatingRepository extends JpaRepository<Rating,Integer> {
    Optional<Rating> findByUserIdAndTmdbIdAndMediaType(
            Integer userId,
            Integer tmdbId,
            String mediaType
    );
    @Query("""
    SELECT AVG(r.feelScore)
    FROM Rating r
    WHERE r.tmdbId = :tmdbId
    AND r.mediaType = :mediaType
""")
    Double getAverageFeelScore(
            @Param("tmdbId") Integer tmdbId,
            @Param("mediaType") String mediaType
    );
    @Query("""
    SELECT AVG(r.storyScore)
    FROM Rating r
    WHERE r.tmdbId = :tmdbId
    AND r.mediaType = :mediaType
""")
    Double getAverageStoryScore(
            @Param("tmdbId") Integer tmdbId,
            @Param("mediaType") String mediaType
    );
    @Query("""
    SELECT AVG(r.impactScore)
    FROM Rating r
    WHERE r.tmdbId = :tmdbId
    AND r.mediaType = :mediaType
""")
    Double getAverageImpactScore(
            @Param("tmdbId") Integer tmdbId,
            @Param("mediaType") String mediaType
    );
    @Query("""
    SELECT COUNT(r)
    FROM Rating r
    WHERE r.tmdbId = :tmdbId
    AND r.mediaType = :mediaType
""")
    Long getTotalRatings(
            @Param("tmdbId") Integer tmdbId,
            @Param("mediaType") String mediaType
    );
}
