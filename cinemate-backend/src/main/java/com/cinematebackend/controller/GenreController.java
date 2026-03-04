package com.cinematebackend.controller;

import com.cinematebackend.entity.Genre;
import com.cinematebackend.service.GenreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/genres")
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    public List<Genre> getGenres(){
        return genreService.getAllGenres();
    }

    @PostMapping
    public Genre createGenre(@RequestBody Genre genre){
        return genreService.createGenre(genre);
    }

    @PostMapping("/sync")
    public String syncGenres(){
        genreService.syncGenresFromTmdb();
        return "Genres synced successfully";
    }

}

