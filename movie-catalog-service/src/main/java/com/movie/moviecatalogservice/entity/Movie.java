package com.movie.moviecatalogservice.entity;

public class Movie {
    private String movieId;
    private String name;

    public Movie(){
        // for handling the data retrieved from the another API
    }
    public Movie(String movieId, String name) {
        this.movieId = movieId;
        this.name = name;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
