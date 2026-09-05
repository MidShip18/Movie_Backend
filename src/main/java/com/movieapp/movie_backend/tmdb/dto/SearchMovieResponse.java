package com.movieapp.movie_backend.tmdb.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
public class SearchMovieResponse {
    private Integer page;
    private List<MovieDto> results;
    @JsonProperty("total_pages")
    private Integer totalPages;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public List<MovieDto> getResults() {
        return results;
    }

    public void setResults(List<MovieDto> results) {
        this.results = results;
    }

    @JsonProperty("total_results")
    private Integer totalResults;
}
