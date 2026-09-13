package com.movieapp.movie_backend.controller;

import com.movieapp.movie_backend.service.MovieService;
import com.movieapp.movie_backend.tmdb.dto.SearchSeriesResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/series")
public class SeriesController {
    private final MovieService movieService;
    public SeriesController(MovieService movieService) {
        this.movieService = movieService;
    }
    @GetMapping("/search")
    public SearchSeriesResponse searchSeries(@RequestParam String query) {
        return movieService.searchSeries(query);

    }
    @GetMapping("/trending")
    public SearchSeriesResponse getTrendingSeries() {
        return movieService.getTrendingSeries();
    }
}
