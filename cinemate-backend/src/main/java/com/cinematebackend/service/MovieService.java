package com.cinematebackend.service;

import com.cinematebackend.dto.MovieDetailsDTO;
import com.cinematebackend.dto.MovieListDTO;
import com.cinematebackend.entity.Movie;
import com.cinematebackend.integration.tmdb.TmdbClient;
import com.cinematebackend.mapper.MovieMapper;
import com.cinematebackend.repository.GenreRepository;
import com.cinematebackend.repository.MovieRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final MovieSyncService movieSyncService;

    public MovieService(MovieRepository movieRepository, MovieSyncService movieSyncService) {
        this.movieRepository = movieRepository;

        this.movieSyncService = movieSyncService;
    }

    public Page<MovieListDTO> getMovies(Pageable pageable) {

        return movieRepository.findAll(pageable)
                .map(MovieMapper::toListDTO);
    }

    public MovieDetailsDTO getMovieById(Long id) {

        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        return MovieMapper.toDetailsDTO(movie);
    }

    public Page<MovieListDTO> getMoviesByGenre(Long genreId, Pageable pageable) {

        return movieRepository.findByGenresTmdbId(genreId, pageable)
                .map(MovieMapper::toListDTO);
    }

    public Page<MovieListDTO> searchMovies(String query, Pageable pageable) {

        Page<Movie> movies = movieRepository
                .findByTitleContainingIgnoreCase(query, pageable);

        // Se já tivermos resultados suficientes na DB
        if (movies.getContent().size() >= pageable.getPageSize()) {
            return movies.map(MovieMapper::toListDTO);
        }

        // Caso contrário vamos buscar ao TMDB
        movieSyncService.fetchMoviesFromTmdb(query, pageable.getPageNumber() + 1);

        // Pesquisar novamente na DB
        return movieRepository
                .findByTitleContainingIgnoreCase(query, pageable)
                .map(MovieMapper::toListDTO);
    }



}
