package com.movieapp.movie_backend.game;

import com.movieapp.movie_backend.tmdb.dto.MovieDto;

public class GameResponse {

    private String gameId;
    private GameMovieResponse movie1;
    private GameMovieResponse movie2;
    private int score;
    private boolean correct;
    private boolean gameOver;

    public GameResponse(
            String gameId,
            MovieDto movie1,
            MovieDto movie2,
            int score,
            boolean correct,
            boolean gameOver
    ) {
        this.gameId = gameId;
        this.movie1 = toResponse(movie1);
        this.movie2 = toResponse(movie2);
        this.score = score;
        this.correct = correct;
        this.gameOver = gameOver;
    }

    private GameMovieResponse toResponse(MovieDto movie) {
        return new GameMovieResponse(
                movie.getId(),
                movie.getTitle(),
                movie.getPosterPath()
        );
    }

    public String getGameId() {
        return gameId;
    }

    public GameMovieResponse getMovie1() {
        return movie1;
    }

    public GameMovieResponse getMovie2() {
        return movie2;
    }

    public int getScore() {
        return score;
    }

    public boolean isCorrect() {
        return correct;
    }

    public boolean isGameOver() {
        return gameOver;
    }
}