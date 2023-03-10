package com.movie.moviecatalogservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class BeanConfig {
    //    creating beans
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
    @Bean
    public WebClient.Builder getWebClientBuilder(){
        return WebClient.builder();
    }
}
