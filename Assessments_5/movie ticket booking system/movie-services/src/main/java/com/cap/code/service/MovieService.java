package com.cap.code.service;

import org.springframework.stereotype.Service;

import com.cap.code.model.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class MovieService {


    private final List<Movie> movies = new ArrayList<>();


    public MovieService() {
        movies.add(new Movie(1L, "Inception", "English", 250.0));
        movies.add(new Movie(2L, "RRR", "Telugu", 200.0));
        movies.add(new Movie(3L, "Interstellar", "English", 300.0));
        movies.add(new Movie(4L, "Dangal", "Hindi", 180.0));
        movies.add(new Movie(5L, "KGF Chapter 2", "Kannada", 220.0));
    }

    public List<Movie> getAllMovies() {
        return movies;
    }
    
    public Optional<Movie> getMovieById(Long id) {
        return movies.stream()
                .filter(movie -> movie.getId().equals(id))
                .findFirst();
    }
}
