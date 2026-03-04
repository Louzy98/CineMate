package com.cinematebackend.controller;

import com.cinematebackend.dto.MovieDetailsDTO;
import com.cinematebackend.dto.MovieListDTO;
import com.cinematebackend.service.MovieService;
import com.cinematebackend.service.MovieSyncService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;
    private final MovieSyncService movieSyncService;

    public MovieController(MovieService movieService, MovieSyncService movieSyncService) {
        this.movieService = movieService;
        this.movieSyncService = movieSyncService;
    }

    @GetMapping
    public Page<MovieListDTO> getMovies(@RequestParam(required = false) Long genreId, Pageable pageable) {
        if (genreId != null) {
            return movieService.getMoviesByGenre(genreId, pageable);
        }
        return movieService.getMovies(pageable);
    }

    @GetMapping("/{id}")
    public MovieDetailsDTO getMovieById(@PathVariable Long id) {
        return movieService.getMovieById(id);
    }

    @GetMapping("/search")
    public Page<MovieListDTO> searchMovies(@RequestParam String q, Pageable pageable) {
        return movieService.searchMovies(q, pageable);
    }

    @PostMapping("/sync/popular")
    public String syncPopularMovies() {

        movieSyncService.syncPopularMovies();

        return "Popular movies synced";
    }

    @PostMapping("/sync/top-rated")
    public String syncTopRatedMovies() {

        movieSyncService.syncTopRatedMovies();

        return "Top rated movies synced";
    }


    @PostMapping("/sync/upcoming")
    public String syncUpcomingMovies() {

        movieSyncService.syncUpcomingMovies();

        return "Upcoming movies synced";
    }


}