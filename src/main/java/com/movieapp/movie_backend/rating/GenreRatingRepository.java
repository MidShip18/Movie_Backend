package com.movieapp.movie_backend.rating;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface GenreRatingRepository extends JpaRepository<GenreRating,Integer>{
    void deleteByRatingId(Integer ratingId);
    List<GenreRating> findByRatingId(Integer ratingId);
    @Query("""
    SELECT gr.genreId, gr.genreName, AVG(gr.score)
    FROM GenreRating gr
    JOIN Rating r ON gr.ratingId = r.id
    WHERE r.tmdbId = :tmdbId
    AND r.mediaType = :mediaType
    GROUP BY gr.genreId, gr.genreName
""")
    List<Object[]> getGenreAverages(
            @Param("tmdbId") Integer tmdbId,
            @Param("mediaType") String mediaType
    );
}
