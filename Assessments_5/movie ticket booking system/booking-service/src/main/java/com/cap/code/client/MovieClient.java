package com.cap.code.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cap.code.fallback.MovieClientFallback;
import com.cap.code.model.MovieResponse;


@FeignClient(
    name = "movie-services",             
    fallback = MovieClientFallback.class
)
public interface MovieClient {

    @GetMapping("/movies/{id}")
    MovieResponse getMovieById(@PathVariable("id") Long id);
}
