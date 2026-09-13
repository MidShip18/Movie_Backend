package com.movieapp.movie_backend.service;
import com.movieapp.movie_backend.tmdb.dto.DetailsDto;
import com.movieapp.movie_backend.tmdb.TmdbClient;
import org.springframework.stereotype.Service;

@Service
public class DetailsService {
    private final TmdbClient tmdbClient;
    public DetailsService(TmdbClient tmdbClient) {
        this.tmdbClient = tmdbClient;
    }
    public DetailsDto getDetails(String type, Integer id){
        return tmdbClient.getDetails(type, id);
    }
}
