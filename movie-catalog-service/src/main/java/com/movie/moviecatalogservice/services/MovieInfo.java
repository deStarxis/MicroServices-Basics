package com.movie.moviecatalogservice.services;

import com.movie.moviecatalogservice.entity.CatalogItem;
import com.movie.moviecatalogservice.entity.Movie;
import com.movie.moviecatalogservice.entity.Rating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieInfo {
    @Autowired
    private RestTemplate restTemplate;
    @HystrixCommand(fallbackMethod = "getFallbackCatalogItem")
    public CatalogItem getCatalogItem(Rating rating) {
        Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class); // 2
        return new CatalogItem(movie.getName(), "This is the description", rating.getRating());
    }
    public CatalogItem getFallbackCatalogItem(Rating rating){
        return new CatalogItem("No movie", "No description", rating.getRating());
    }
}
