package com.movieapp.movie_backend.game;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/game")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/start")
    public GameResponse startGame() {
        return gameService.startGame();
    }

    @PostMapping("/guess")
    public GameResponse guess(@RequestBody GuessRequest request) {

        GameState gameState = gameService.getGame(request.getGameId());

        GuessResult result = gameService.guess(
                gameState,
                request.getChoice()
        );

        return new GameResponse(
                request.getGameId(),
                result.getGameState().getMovie1(),
                result.getGameState().getMovie2(),
                result.getGameState().getScore(),
                result.isCorrect(),
                !result.getGameState().isActive()
        );
    }
}