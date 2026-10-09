package com.movieapp.movie_backend.watchlist;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/watchlist")
public class WatchlistController {
    private final WatchlistService watchlistService;
    public WatchlistController(WatchlistService watchlistService) {
        this.watchlistService = watchlistService;
    }

    @PostMapping("/{tmdbId}")
    public Watchlist addToWatchlist(@PathVariable Integer tmdbId, @RequestParam String mediaType){
        return watchlistService.addToWatchlist(tmdbId, mediaType);
    }

    @GetMapping
    public List<Watchlist> getMyWatchlist(){
        return watchlistService.getMyWatchlist();
    }

    @DeleteMapping("/{tmdbId}")
    public void removeFromWatchlist(@PathVariable Integer tmdbId, @RequestParam String mediaType){
        watchlistService.removeFromWatchlist(tmdbId, mediaType);
    }
}
