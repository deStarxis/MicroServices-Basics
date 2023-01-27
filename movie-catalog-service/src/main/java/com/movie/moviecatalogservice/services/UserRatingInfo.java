package com.movie.moviecatalogservice.services;

import com.movie.moviecatalogservice.entity.Rating;
import com.movie.moviecatalogservice.entity.UserRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class UserRatingInfo {
    @Autowired
    private RestTemplate restTemplate;
    @HystrixCommand(fallbackMethod = "getFallbackUserRating")
    public UserRating getUserRating(String userId) {
        return restTemplate.getForObject("http://rating-data-service/ratings/users/" + userId, UserRating.class);
    }
    public UserRating getFallbackUserRating(@PathVariable String userId){
        UserRating userRating = new UserRating();
        userRating.setUserId(userId);
        userRating.setRatings(Arrays.asList(new Rating("0",0)));
        return userRating;
    }
}
