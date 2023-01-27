package com.movie.ratingdataservice.controller;

import com.movie.ratingdataservice.entity.Rating;
import com.movie.ratingdataservice.entity.UserRating;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratings")
@CrossOrigin
public class RatingsController {
    @GetMapping("/{movieId}")
    public Rating getMovieRating(@PathVariable String movieId){
        // get a rating for movie
        return new Rating(movieId,5);
    }

    @GetMapping("/users/{userId}")
    public UserRating getUserRatings(@PathVariable String userId){
//        returning the user ratings
        List<Rating> ratings = Arrays.asList(
                new Rating("123",4),
                new Rating("567",5)
        );
        UserRating  userRating = new UserRating();
        userRating.setUserId(userId);
        userRating.setRatings(ratings);
        return userRating;
    }
}
