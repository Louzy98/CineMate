package com.cinematebackend.dto;

import java.util.List;

public class TmdbMovie {

    private Long id;
    private String title;
    private String overview;
    private String poster_path;
    private String release_date;
    private Double vote_average;
    private List<Long> genre_ids;

    public Long getId() { return id; }

    public String getTitle() { return title; }

    public String getOverview() { return overview; }

    public String getPoster_path() { return poster_path; }

    public String getRelease_date() { return release_date; }

    public Double getVote_average() { return vote_average; }

    public List<Long> getGenre_ids() { return genre_ids; }

}