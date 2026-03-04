package com.cinematebackend.dto;

import java.time.LocalDate;
import java.util.Set;

public class MovieDetailsDTO {

    private Long id;
    private String title;
    private String overview;
    private String posterPath;
    private LocalDate releaseDate;
    private Double voteAverage;
    private Set<String> genres;

    public MovieDetailsDTO(Long id, String title, String overview, String posterPath,
                           LocalDate releaseDate, Double voteAverage, Set<String> genres) {

        this.id = id;
        this.title = title;
        this.overview = overview;
        this.posterPath = posterPath;
        this.releaseDate = releaseDate;
        this.voteAverage = voteAverage;
        this.genres = genres;
    }

    public Long getId() { return id; }

    public String getTitle() { return title; }

    public String getOverview() { return overview; }

    public String getPosterPath() { return posterPath; }

    public LocalDate getReleaseDate() { return releaseDate; }

    public Double getVoteAverage() { return voteAverage; }

    public Set<String> getGenres() { return genres; }
}