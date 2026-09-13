package com.movieapp.movie_backend.tmdb.dto;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Setter
@NoArgsConstructor
public class DetailsDto {
    private int id;
    private String type;
    private String title;
    private String overview;
    @JsonProperty("poster_path")
    private String posterPath;
    @JsonProperty("backdrop_path")
    private String backdropPath;
    private String date;
    @JsonProperty("vote_average")
    private Double voteAverage;
    private List<GenreDto> genres;
    private Integer runtime;

    @JsonProperty("number_of_seasons")
    private Integer numberOfSeasons;
    @JsonProperty("number_of_episodes")
    private Integer numberOfEpisodes;

}
