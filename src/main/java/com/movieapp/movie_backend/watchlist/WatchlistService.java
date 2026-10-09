package com.movieapp.movie_backend.watchlist;

import com.movieapp.movie_backend.entity.User;
import com.movieapp.movie_backend.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WatchlistService {
    private final WatchlistRepository watchlistRepository;
    private final UserRepository userRepository;
    public WatchlistService(WatchlistRepository watchlistRepository, UserRepository userRepository) {
        this.watchlistRepository = watchlistRepository;
        this.userRepository = userRepository;
    }
    public Watchlist addToWatchlist(Integer tmdbId, String mediaType) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new RuntimeException("user not found"));

        if(watchlistRepository.findByUserIdAndTmdbIdAndMediaType(user.getId(), tmdbId, mediaType).isPresent()) {
            throw new RuntimeException("Already in watchlist");
        }
        Watchlist watchlist = new Watchlist();
        watchlist.setTmdbId(tmdbId);
        watchlist.setMediaType(mediaType);
        watchlist.setUserId(user.getId());
        watchlist.setAddedAt(LocalDateTime.now());

        return watchlistRepository.save(watchlist);
    }
    public List<Watchlist> getMyWatchlist() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user =  userRepository.findByEmail(email)
                .orElseThrow(()-> new RuntimeException("user not found"));

        return watchlistRepository.findByUserId(user.getId());
    }
    public void removeFromWatchlist(Integer tmdbId, String mediaType) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new RuntimeException("user not found"));

        Watchlist watchlist = watchlistRepository.findByUserIdAndTmdbIdAndMediaType(user.getId(), tmdbId, mediaType).orElseThrow(() -> new RuntimeException("watchlist not found"));
        watchlistRepository.delete(watchlist);
    }
}