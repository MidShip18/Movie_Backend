package com.movieapp.movie_backend.tmdb;
import com.movieapp.movie_backend.tmdb.dto.DetailsDto;
import com.movieapp.movie_backend.tmdb.dto.SearchMovieResponse;
import com.movieapp.movie_backend.tmdb.dto.SearchSeriesResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.movieapp.movie_backend.tmdb.dto.MovieDto;
import com.movieapp.movie_backend.tmdb.dto.SeriesDto;
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
    public SearchSeriesResponse searchSeries(String query) {
        String url = UriComponentsBuilder.fromUriString(baseUrl).path("/search/tv")
                .queryParam("api_key", apiKey).queryParam("query", query).toUriString();
        return restTemplate.getForObject(url, SearchSeriesResponse.class);
    }
    public SearchMovieResponse getTrendingMovie() {
        String url = UriComponentsBuilder.fromUriString(baseUrl)
                .path("/trending/movie/day")
                .queryParam("api_key", apiKey).toUriString();

        return restTemplate.getForObject(url, SearchMovieResponse.class);
    }
    public SearchMovieResponse getPopularMovies(int page) {

        String url = UriComponentsBuilder
                .fromUriString(baseUrl)
                .path("/movie/popular")
                .queryParam("api_key", apiKey)
                .queryParam("page", page)
                .toUriString();

        return restTemplate.getForObject(url, SearchMovieResponse.class);
    }
    public SearchSeriesResponse getTrendingSeries() {
        String url = UriComponentsBuilder.fromUriString(baseUrl)
                .path("/trending/tv/day").queryParam("api_key", apiKey).toUriString();

        return restTemplate.getForObject(url, SearchSeriesResponse.class);
    }
    public DetailsDto getDetails(String type, Integer id) {

        String path;

        if (type.equals("movie")) {
            path = "/movie/" + id;
        } else if (type.equals("series")) {
            path = "/tv/" + id;
        } else {
            throw new IllegalArgumentException("Invalid type: " + type);
        }

        String url = UriComponentsBuilder
                .fromUriString(baseUrl)
                .path(path)
                .queryParam("api_key", apiKey)
                .toUriString();

        DetailsDto details = new DetailsDto();

        if (type.equals("movie")) {

            MovieDto movie =
                    restTemplate.getForObject(url, MovieDto.class);

            details.setId(movie.getId());
            details.setType("movie");
            details.setTitle(movie.getTitle());
            details.setOverview(movie.getOverview());
            details.setPosterPath(movie.getPosterPath());
            details.setBackdropPath(movie.getBackdropPath());
            details.setDate(movie.getReleaseDate());
            details.setVoteAverage(movie.getVoteAverage());
            details.setGenres(movie.getGenres());
            details.setRuntime(movie.getRuntime());

        } else {

            SeriesDto series =
                    restTemplate.getForObject(url, SeriesDto.class);

            details.setId(series.getId());
            details.setType("series");
            details.setTitle(series.getName());
            details.setOverview(series.getOverview());
            details.setPosterPath(series.getPosterpath());
            details.setBackdropPath(series.getBackdroppath());
            details.setDate(series.getFirstAirDate());
            details.setVoteAverage(series.getVoteAverage());
            details.setGenres(series.getGenres());
            details.setNumberOfSeasons(series.getNumberOfSeasons());
            details.setNumberOfEpisodes(series.getNumberOfEpisodes());
        }

        return details;
    }
}
