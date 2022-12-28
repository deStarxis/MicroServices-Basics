package com.movie.ratingdataservice.controller;

import com.movie.ratingdataservice.entity.Rating;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratings")
@CrossOrigin
public class RatingsController {
    @GetMapping("/{movieId}")
    public Rating getMovieRating(@PathVariable String movieId){
        return new Rating(movieId,5);
    }

    @GetMapping("/users/{userId}")
    public List<Rating> getUserRatings(@PathVariable String userId){
        List<Rating> ratings = Arrays.asList(
                new Rating("123",4),
                new Rating("567",5)
        );
        return ratings;
    }
}
