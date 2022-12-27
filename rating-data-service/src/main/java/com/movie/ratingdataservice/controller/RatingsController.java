package com.movie.ratingdataservice.controller;

import com.movie.ratingdataservice.entity.Rating;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ratings")
@CrossOrigin
public class RatingsController {
    @GetMapping("/{movieId}")
    public Rating getMovieRating(@PathVariable String movieId){
        return new Rating(movieId,5);
    }
}
