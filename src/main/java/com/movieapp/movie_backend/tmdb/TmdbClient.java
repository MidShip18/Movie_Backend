package com.movieapp.movie_backend.tmdb;
import com.movieapp.movie_backend.tmdb.dto.SearchMovieResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
@Component
public class TmdbClient {
    private final RestTemplate restTemplate;
    @Value("${tmdb.api.key}")
    private String apiKey;
    @Value("${tmdb.base-url}")
    private String baseUrl;
    public TmdbClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public SearchMovieResponse searchMovie(String query) {
        String url = UriComponentsBuilder.fromUriString(baseUrl).path("/search/movie")
                .queryParam("api_key", apiKey).queryParam("query", query).toUriString();
        return restTemplate.getForObject(url, SearchMovieResponse.class);
    }
    public SearchMovieResponse getTrendingMovie() {
        String url = UriComponentsBuilder.fromUriString(baseUrl)
                .path("/trending/movie/day")
                .queryParam("api_key", apiKey).toUriString();
        System.out.println(url);
        return restTemplate.getForObject(url, SearchMovieResponse.class);
    }
}
