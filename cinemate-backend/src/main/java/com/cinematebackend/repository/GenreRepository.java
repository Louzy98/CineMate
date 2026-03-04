package com.cinematebackend.repository;

import com.cinematebackend.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre,Long> {
    Optional<Genre> findByTmdbId(Long tmdbId);
}
