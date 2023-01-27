package com.movie.moviecatalogservice.configuration;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class BeanConfig {
    //    creating beans
    @Bean
    @LoadBalanced // service discovery in a load balanced way looks for the service name hints
    public RestTemplate getRestTemplate(){
//        return new RestTemplate();
//        using request timeout
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(3000);
        return new RestTemplate(clientHttpRequestFactory);
    }
    @Bean
    public WebClient.Builder getWebClientBuilder(){
        return WebClient.builder();
    }
}
