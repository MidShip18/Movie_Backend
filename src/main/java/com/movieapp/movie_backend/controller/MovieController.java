package com.movieapp.movie_backend.controller;
import com.movieapp.movie_backend.service.MovieService;
import com.movieapp.movie_backend.tmdb.dto.SearchMovieResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
@RestController
@RequestMapping("/api/movies")
public class MovieController {
    private final MovieService movieService;
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }
    @GetMapping("/search")
    public SearchMovieResponse searchMovie(@RequestParam String query){
        // temp
        System.out.println("MOVIE CONTROLLER HIT: " + query);
        try {
            return movieService.searchMovie(query);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
     //   return movieService.searchMovie(query);
    }
    @GetMapping("/trending")
    public SearchMovieResponse getTrendingMovies(){
        return movieService.getTrendingMovies();
    }
}
