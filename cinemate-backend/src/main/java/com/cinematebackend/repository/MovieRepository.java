package com.cinematebackend.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cinematebackend.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    Optional<Movie> findByTmdbId(Long tmdbId);
    Page<Movie> findAll(Pageable pageable);
    Page<Movie> findByGenresTmdbId(Long genreId, Pageable pageable);
    Page<Movie> findByTitleContainingIgnoreCase(String title, Pageable pageable);

}