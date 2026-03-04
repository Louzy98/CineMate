package com.cinematebackend.dto;

import java.time.LocalDate;

public class MovieListDTO {

    private Long id;
    private String title;
    private String posterPath;
    private LocalDate releaseDate;
    private Double voteAverage;

    public MovieListDTO(Long id, String title, String posterPath, LocalDate releaseDate, Double voteAverage) {
        this.id = id;
        this.title = title;
        this.posterPath = posterPath;
        this.releaseDate = releaseDate;
        this.voteAverage = voteAverage;
    }

    public Long getId() { return id; }

    public String getTitle() { return title; }

    public String getPosterPath() { return posterPath; }

    public LocalDate getReleaseDate() { return releaseDate; }

    public Double getVoteAverage() { return voteAverage; }
}