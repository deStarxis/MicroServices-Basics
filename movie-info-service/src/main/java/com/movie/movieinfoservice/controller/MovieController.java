package com.movie.movieinfoservice.controller;

import com.movie.movieinfoservice.entity.Movie;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
@CrossOrigin
public class MovieController {
    @GetMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable String movieId){
        return new Movie(movieId,"A new Movie");
    }
}
