package com.movie.moviecatalogservice.controller;

import com.movie.moviecatalogservice.entity.CatalogItem;
import com.movie.moviecatalogservice.entity.Movie;
import com.movie.moviecatalogservice.entity.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalogs")
@CrossOrigin
public class MovieCatalogController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private WebClient.Builder webClientBuilder;
    @GetMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable String userId){
//        using this for calling the API
        //get all rated movie ids
        List<Rating> ratings = Arrays.asList(
                new Rating("123",4),
                new Rating("567",5)
        );
        return ratings.stream().map(rating -> {
//            Movie movie = restTemplate.getForObject("http://localhost:8082/movies/"+rating.getMovieId(), Movie.class);
            Movie movie = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8082/movies/"+rating.getMovieId())
                    .retrieve()
                    .bodyToMono(Movie.class) // mono is kind of a promise
                    .block();
            return new CatalogItem(movie.getName(),"This is the description",rating.getRating());
        }).collect(Collectors.toList());
        //for each movie id call movie info service and get details

        //put them all together
//        return Collections.singletonList(
//                new CatalogItem("Harry Potter","This is the very nice movie",5)
////                new CatalogItem("Transformer","This is another nice movie",4),
////                new CatalogItem("Avatar","This is the another nice movie",5),
////                new CatalogItem("MIB","This is a nice movie",4)
//        );
    }
}
