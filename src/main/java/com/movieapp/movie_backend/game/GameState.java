package com.movieapp.movie_backend.game;
import com.movieapp.movie_backend.tmdb.dto.MovieDto;
public class GameState {
    private MovieDto movie1;
    private MovieDto movie2;
    private int score;
    private boolean active;

    public GameState(MovieDto movie1, MovieDto movie2) {
        this.movie1 = movie1;
        this.movie2 = movie2;
        this.score = 0;
        this.active = true;
    }

    public MovieDto getMovie1() {
        return movie1;
    }

    public MovieDto getMovie2() {
        return movie2;
    }

    public int getScore() {
        return score;
    }

    public boolean isActive() {
        return active;
    }

    public void increaseScore() {
        score++;
    }

    public void nextRound(MovieDto newMovie) {
        movie1 = movie2;
        movie2 = newMovie;
    }

    public void endGame() {
        active = false;
    }
}
