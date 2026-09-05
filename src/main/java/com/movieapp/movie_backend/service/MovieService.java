package com.movieapp.movie_backend.service;
import com.movieapp.movie_backend.tmdb.TmdbClient;
import com.movieapp.movie_backend.tmdb.dto.SearchMovieResponse;
import org.springframework.stereotype.Service;
@Service
public class MovieService {
    private TmdbClient tmdbClient;
    public MovieService(TmdbClient tmdbClient) {
        this.tmdbClient = tmdbClient;
    }
    public SearchMovieResponse searchMovie(String query) {
        return tmdbClient.searchMovie(query);
    }
    public SearchMovieResponse getTrendingMovies() {
        return tmdbClient.getTrendingMovie();
    }
}
