package com.cinematebackend.integration.tmdb;

import com.cinematebackend.dto.TmdbGenreResponse;
import com.cinematebackend.dto.TmdbMovieResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class TmdbClient {

    @Value("${tmdb.api.key}")
    private String apiKey;

    @Value("${tmdb.api.url}")
    private String baseUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public TmdbGenreResponse getGenres() {

        String url = baseUrl +
                "/genre/movie/list?api_key=" + apiKey;

        return restTemplate.getForObject(url, TmdbGenreResponse.class);
    }

    public TmdbMovieResponse searchMovies(String query, int page) {

        String url = baseUrl +
                "/search/movie?api_key=" + apiKey +
                "&query=" + query +
                "&page=" + page;

        return restTemplate.getForObject(url, TmdbMovieResponse.class);
    }

    public TmdbMovieResponse getMovies(String category, int page) {

        String url = baseUrl +
                "/movie/" + category +
                "?api_key=" + apiKey +
                "&page=" + page;

        return restTemplate.getForObject(url, TmdbMovieResponse.class);
    }

}