package com.movie.moviecatalogservice.controller;

import com.movie.moviecatalogservice.entity.CatalogItem;
import com.movie.moviecatalogservice.entity.Movie;
import com.movie.moviecatalogservice.entity.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalogs")
@CrossOrigin
public class MovieCatalogController {
    @Autowired
    private RestTemplate restTemplate;

//    @Autowired
//    private DiscoveryClient discoveryClient;

    @GetMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable String userId){
//        using this for calling the API
//        using rest template
//        both of these are synchronous
        //get all rated movie ids
        UserRating ratings = restTemplate.getForObject("http://rating-data-service/ratings/users/"+userId, UserRating.class); //1
        return ratings.getRatings().stream().map(rating -> {
            Movie movie = restTemplate.getForObject("http://movie-info-service/movies/"+rating.getMovieId(), Movie.class); // 2
            return new CatalogItem(movie.getName(),"This is the description",rating.getRating());
        }).collect(Collectors.toList());
    }
}
