package com.movieapp.movie_backend.game;

public class GameMovieResponse {

    private Integer id;
    private String title;
    private String posterPath;

    public GameMovieResponse(Integer id, String title, String posterPath) {
        this.id = id;
        this.title = title;
        this.posterPath = posterPath;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPosterPath() {
        return posterPath;
    }
}