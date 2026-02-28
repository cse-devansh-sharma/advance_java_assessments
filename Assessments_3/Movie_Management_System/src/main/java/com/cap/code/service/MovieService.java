package com.cap.code.service;

import org.springframework.stereotype.Service;
import com.cap.code.repository.*;
import java.util.List;
import com.cap.code.model.*;

@Service
public class MovieService {
	private MovieRepository movieRepository;
	
	public MovieService(MovieRepository movieRepository) {
		this.movieRepository=movieRepository;
	}
	
	public List<Movie> getAllMovies(){
		return movieRepository.findAll();
	}
	
	public String saveMovie(Movie movie) {
		movieRepository.save(movie);
		return "Movie Saved";
	}
	
	public String updateMovies(Integer id, Movie movie) {
		if(movieRepository.existsById(id)) {
			Movie existing=movieRepository.findById(id).orElse(null);
			existing.setDirector(movie.getDirector());
			existing.setGenre(movie.getGenre());
			existing.setRating(movie.getRating());
			existing.setReleaseYear(movie.getReleaseYear());
			existing.setTitle(movie.getTitle());
			
			movieRepository.save(existing);
			return "Movie Details Updated";
		}else {
			return "Movie Not Found";
		}
	}
	
	public String deleteMovie(Integer Id) {
		if(movieRepository.existsById(Id)) {
			movieRepository.deleteById(Id);
			return "Movie Deleted";
		}else {
			return "Movie Not Found";
		}
	}
	public Movie getMovieById(Integer id) {
	    return movieRepository.findById(id).orElse(null);
	}
	
	
}
