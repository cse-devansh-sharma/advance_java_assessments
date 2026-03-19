package com.cap.code.fallback;

import org.springframework.stereotype.Component;

import com.cap.code.client.MovieClient;
import com.cap.code.model.MovieResponse;

@Component  // Must be a Spring bean so Feign can inject it
public class MovieClientFallback implements MovieClient {

    
    @Override
    public MovieResponse getMovieById(Long id) {
        
        System.out.println("[CIRCUIT BREAKER] Movie Service is DOWN. Returning fallback for movieId: " + id);
        return new MovieResponse(id, "Service Unavailable", "N/A", 0.0);
    }
}
