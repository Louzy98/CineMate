package com.cinematebackend.service;

import com.cinematebackend.dto.TmdbMovie;
import com.cinematebackend.dto.TmdbMovieResponse;
import com.cinematebackend.entity.Genre;
import com.cinematebackend.entity.Movie;
import com.cinematebackend.integration.tmdb.TmdbClient;
import com.cinematebackend.mapper.MovieMapper;
import com.cinematebackend.repository.GenreRepository;
import com.cinematebackend.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Service
public class MovieSyncService {

    private final TmdbClient tmdbClient;
    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;

    public MovieSyncService(TmdbClient tmdbClient,
                            MovieRepository movieRepository,
                            GenreRepository genreRepository) {

        this.tmdbClient = tmdbClient;
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
    }

    public void syncMoviesByCategory(String category) {

        TmdbMovieResponse response = tmdbClient.getMovies(category, 1);

        if (response == null || response.getResults() == null) {
            return;
        }

        for (TmdbMovie tmdbMovie : response.getResults()) {

            boolean exists = movieRepository
                    .findByTmdbId(tmdbMovie.getId())
                    .isPresent();

            if (!exists) {

                Set<Genre> genres = new HashSet<>();

                for (Long genreId : tmdbMovie.getGenre_ids()) {

                    genreRepository
                            .findByTmdbId(genreId)
                            .ifPresent(genres::add);
                }

                Movie movie = MovieMapper.fromTmdbMovie(tmdbMovie, genres);

                movieRepository.save(movie);
            }
        }
    }

    public void syncPopularMovies() {
        syncMoviesByCategory("popular");
    }
    public void syncTopRatedMovies() {
        syncMoviesByCategory("top_rated");
    }
    public void syncUpcomingMovies() {
        syncMoviesByCategory("upcoming");
    }

    public void fetchMoviesFromTmdb(String query, int page) {

        TmdbMovieResponse response = tmdbClient.searchMovies(query, page);

        for (TmdbMovie tmdbMovie : response.getResults()) {

            boolean exists = movieRepository
                    .findByTmdbId(tmdbMovie.getId())
                    .isPresent();

            if (!exists) {

                Set<Genre> genres = new HashSet<>();

                for (Long genreId : tmdbMovie.getGenre_ids()) {

                    genreRepository
                            .findByTmdbId(genreId)
                            .ifPresent(genres::add);
                }

                Movie movie = MovieMapper.fromTmdbMovie(tmdbMovie, genres);

                movieRepository.save(movie);
            }
        }
    }
}