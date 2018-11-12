package com.cinema.api.cinema.controller;

import com.cinema.api.cinema.model.entity.Movie;
import com.cinema.api.cinema.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movies")
@AllArgsConstructor
public class MovieController {

     private final MovieService movieService;

     @GetMapping
     public List<Movie> getAll() {
          return movieService.getAll();
     }

     @PostMapping
     public Movie save(@RequestBody Movie movie) {
          return movieService.save(movie);
     }
}
