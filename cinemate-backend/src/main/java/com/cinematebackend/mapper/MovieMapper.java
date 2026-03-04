package com.cinematebackend.mapper;

import com.cinematebackend.dto.MovieDetailsDTO;
import com.cinematebackend.dto.MovieListDTO;
import com.cinematebackend.dto.TmdbMovie;
import com.cinematebackend.entity.Genre;
import com.cinematebackend.entity.Movie;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

public class MovieMapper {

    public static MovieListDTO toListDTO(Movie movie) {

        return new MovieListDTO(
                movie.getId(),
                movie.getTitle(),
                movie.getPosterPath(),
                movie.getReleaseDate(),
                movie.getVoteAverage()
        );
    }

    public static MovieDetailsDTO toDetailsDTO(Movie movie) {

        Set<String> genres = movie.getGenres()
                .stream()
                .map(Genre::getName)
                .collect(Collectors.toSet());

        return new MovieDetailsDTO(
                movie.getId(),
                movie.getTitle(),
                movie.getOverview(),
                movie.getPosterPath(),
                movie.getReleaseDate(),
                movie.getVoteAverage(),
                genres
        );
    }

    public static Movie fromTmdbMovie(TmdbMovie tmdbMovie, Set<Genre> genres) {

        Movie movie = new Movie();

        movie.setTmdbId(tmdbMovie.getId());
        movie.setTitle(tmdbMovie.getTitle());
        movie.setOverview(tmdbMovie.getOverview());
        movie.setPosterPath(tmdbMovie.getPoster_path());
        movie.setVoteAverage(tmdbMovie.getVote_average());

        if (tmdbMovie.getRelease_date() != null &&
                !tmdbMovie.getRelease_date().isEmpty()) {

            movie.setReleaseDate(LocalDate.parse(tmdbMovie.getRelease_date()));
        }

        movie.setGenres(genres);

        return movie;
    }
}