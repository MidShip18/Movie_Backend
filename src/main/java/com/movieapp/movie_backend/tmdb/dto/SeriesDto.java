package com.movieapp.movie_backend.tmdb.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SeriesDto {
    private int id;
    private String name;
    private String overview;
    @JsonProperty("poster_path")
    private String posterpath;
    @JsonProperty("backdrop_path")
    private String backdroppath;
    @JsonProperty("first_air_date")
    private String firstAirDate;
    @JsonProperty("vote_average")
    private Double voteAverage;

    private List<GenreDto> genres;

    @JsonProperty("number_of_seasons")
    private Integer numberOfSeasons;

    @JsonProperty("number_of_episodes")
    private Integer numberOfEpisodes;

}
