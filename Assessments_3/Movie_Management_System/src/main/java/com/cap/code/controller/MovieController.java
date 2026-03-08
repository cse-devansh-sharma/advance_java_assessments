package com.cap.code.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cap.code.service.*;
import java.util.List;
import com.cap.code.model.*;

@Controller
@RequestMapping("/movie")
public class MovieController {
	
	private MovieService movieService;
	
	public MovieController(MovieService movieService) {
		this.movieService=movieService;
	}
	
	@GetMapping("/list")
	public ModelAndView getAllMovies() {
	    List<Movie> movies = movieService.getAllMovies();
	    ModelAndView mv = new ModelAndView();
	    mv.addObject("movies", movies);
	    mv.setViewName("movies");

	    return mv;
	}
	
	@GetMapping("/create")
	public String showForm(Model model) {
	    model.addAttribute("movie", new Movie());
	    return "createMovie";
	}

	@PostMapping("/create")
	public String saveMovie(@ModelAttribute Movie movie) {
	    movieService.saveMovie(movie);
	    return "redirect:/movie/list";  
	}
	
	@GetMapping("/edit/{id}")
	public String showEditForm(@PathVariable Integer id, Model model) {
	    Movie movie = movieService.getMovieById(id);
	    if (movie == null) {
	        return "redirect:/movie/list";
	    }
	    model.addAttribute("movie", movie);
	    return "editMovie";
	}

	@PostMapping("/edit/{id}")
	public String updateMovie(@PathVariable Integer id, @ModelAttribute Movie movie) {
	    movieService.updateMovies(id, movie);
	    return "redirect:/movie/list";
	}

	@GetMapping("/delete/{id}")
	public String deleteMovie(@PathVariable Integer id) {
	    movieService.deleteMovie(id);
	    return "redirect:/movie/list";
	}
	
	
}
