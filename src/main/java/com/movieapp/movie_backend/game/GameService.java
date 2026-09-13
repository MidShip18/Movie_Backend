package com.movieapp.movie_backend.game;
import com.movieapp.movie_backend.tmdb.TmdbClient;
import com.movieapp.movie_backend.tmdb.dto.MovieDto;
import com.movieapp.movie_backend.tmdb.dto.SearchMovieResponse;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

    @Service
    public class GameService {

        private final TmdbClient tmdbClient;
        private final Random random = new Random();
        private final Map<String, GameState> games = new ConcurrentHashMap<>();

        public GameService(TmdbClient tmdbClient) {
            this.tmdbClient = tmdbClient;
        }

        public GameResponse startGame() {

            SearchMovieResponse response = tmdbClient.getPopularMovies(1);

            List<MovieDto> movies = response.getResults();

            MovieDto movie1 = getRandomMovie(movies);
            MovieDto movie2 = getRandomMovie(movies);

            while (movie1.getId().equals(movie2.getId())) {
                movie2 = getRandomMovie(movies);
            }

            GameState gameState = new GameState(movie1, movie2);

            String gameId = UUID.randomUUID().toString();

            games.put(gameId, gameState);

            return new GameResponse(
                    gameId,
                    movie1,
                    movie2,
                    gameState.getScore(),
                    false,
                    false
            );
        }
        public GuessResult guess(GameState gameState, String choice) {
            if (!gameState.isActive()) {
                throw new IllegalStateException("Game is already over");
            }

            MovieDto movie1 = gameState.getMovie1();
            MovieDto movie2 = gameState.getMovie2();

            boolean correct;

            if (choice.equalsIgnoreCase("movie1")) {

                correct = movie1.getVoteAverage() > movie2.getVoteAverage();

            } else if (choice.equalsIgnoreCase("movie2")) {

                correct = movie2.getVoteAverage() > movie1.getVoteAverage();

            } else {

                throw new IllegalArgumentException(
                        "Choice must be movie1 or movie2"
                );
            }

            if (correct) {

                gameState.increaseScore();

                MovieDto newMovie = getNewMovie(
                        movie1.getId(),
                        movie2.getId()
                );

                gameState.nextRound(newMovie);

            } else {

                gameState.endGame();
            }

            return new GuessResult(gameState, correct);
        }
        private MovieDto getNewMovie(Integer movie1Id, Integer movie2Id) {

            SearchMovieResponse response = tmdbClient.getPopularMovies(1);

            List<MovieDto> movies = response.getResults();

            MovieDto newMovie = getRandomMovie(movies);

            while (newMovie.getId().equals(movie1Id)
                    || newMovie.getId().equals(movie2Id)) {

                newMovie = getRandomMovie(movies);
            }

            return newMovie;
        }
        public GameState getGame(String gameId) {

            GameState gameState = games.get(gameId);

            if (gameState == null) {
                throw new IllegalArgumentException("Game not found");
            }

            return gameState;
        }
        private MovieDto getRandomMovie(List<MovieDto> movies) {
            return movies.get(random.nextInt(movies.size()));
        }
}
