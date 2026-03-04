package com.cinematebackend.service;

import com.cinematebackend.dto.TmdbGenre;
import com.cinematebackend.dto.TmdbGenreResponse;
import com.cinematebackend.entity.Genre;
import com.cinematebackend.integration.tmdb.TmdbClient;
import com.cinematebackend.repository.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {

    private final GenreRepository genreRepository;
    private final TmdbClient tmdbClient;

    public GenreService(GenreRepository genreRepository, TmdbClient tmdbClient) {
        this.genreRepository = genreRepository;
        this.tmdbClient = tmdbClient;
    }

    public List<Genre> getAllGenres(){
        return genreRepository.findAll();
    }

    public Genre createGenre(Genre genre){
        return genreRepository.save(genre);
    }

    public void syncGenresFromTmdb() {

        TmdbGenreResponse response = tmdbClient.getGenres();

        for (TmdbGenre tmdbGenre : response.getGenres()) {

            boolean exists = genreRepository.findByTmdbId(tmdbGenre.getId()).isPresent();

            if (!exists) {

                Genre genre = new Genre();
                genre.setTmdbId(tmdbGenre.getId());
                genre.setName(tmdbGenre.getName());

                genreRepository.save(genre);
            }
        }
    }
}


