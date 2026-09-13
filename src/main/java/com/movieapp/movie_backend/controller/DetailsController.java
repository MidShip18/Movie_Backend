package com.movieapp.movie_backend.controller;
import com.movieapp.movie_backend.service.DetailsService;
import com.movieapp.movie_backend.tmdb.dto.DetailsDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/details")
public class DetailsController {
    private final DetailsService detailsService;
    public DetailsController(DetailsService detailsService) {
        this.detailsService = detailsService;
    }
    @GetMapping("/{type}/{id}")
    public DetailsDto getDetails(@PathVariable String type, @PathVariable Integer id){
        return detailsService.getDetails(type, id);
    }
}
