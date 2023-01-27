package com.movie.moviecatalogservice.controller;

import com.movie.moviecatalogservice.entity.CatalogItem;
import com.movie.moviecatalogservice.entity.Movie;
import com.movie.moviecatalogservice.entity.Rating;
import com.movie.moviecatalogservice.entity.UserRating;
import com.movie.moviecatalogservice.services.MovieInfo;
import com.movie.moviecatalogservice.services.UserRatingInfo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
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

//    granular fallback refactoring
    @Autowired
    private MovieInfo movieInfo;

    @Autowired
    private UserRatingInfo userRatingInfo;
    @GetMapping("/{userId}")
//    @HystrixCommand(fallbackMethod = "getFallbackCatalog") // no need of this
    public List<CatalogItem> getCatalog(@PathVariable String userId) {
//        using this for calling the API
//        using rest template
//        both of these are synchronous
        //get all rated movie ids
        UserRating ratings = userRatingInfo.getUserRating(userId); //1
        return ratings.getRatings().stream().map(rating -> movieInfo.getCatalogItem(rating)).collect(Collectors.toList());
    }
//    @HystrixCommand(fallbackMethod = "getFallbackCatalogItem")
//    private CatalogItem getCatalogItem(Rating rating) {
//        Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class); // 2
//        return new CatalogItem(movie.getName(), "This is the description", rating.getRating());
//    }
//    @HystrixCommand(fallbackMethod = "getFallbackUserRating")
//    private UserRating getUserRating(String userId) {
//        return restTemplate.getForObject("http://rating-data-service/ratings/users/" + userId, UserRating.class);
//    }
//    private CatalogItem getFallbackCatalogItem(Rating rating){
//        return new CatalogItem("No movie", "No description", rating.getRating());
//    }
//    private UserRating getFallbackUserRating(@PathVariable String userId){
//        UserRating userRating = new UserRating();
//        userRating.setUserId(userId);
//        userRating.setRatings(Arrays.asList(new Rating("0",0)));
//        return userRating;
//    }
//    no need of this
//    private List<CatalogItem> getFallbackCatalog(@PathVariable String userId) {
//        return Arrays.asList(new CatalogItem("No movie", "No description", 0));
//    }
}
