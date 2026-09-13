package com.movieapp.movie_backend.game;

public class GuessResult {

    private GameState gameState;
    private boolean correct;

    public GuessResult(GameState gameState, boolean correct) {
        this.gameState = gameState;
        this.correct = correct;
    }

    public GameState getGameState() {
        return gameState;
    }

    public boolean isCorrect() {
        return correct;
    }
}
