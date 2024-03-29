package com.cinema.api.cinema.controller;

import com.cinema.api.cinema.model.entity.Movie;
import com.cinema.api.cinema.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
@AllArgsConstructor
public class MovieController {

     private final MovieService movieService;

     @GetMapping
     public Page<Movie> getAll(Pageable pageable) {
          return movieService.getAll(pageable);
     }

     @PostMapping
     public Movie save(@RequestBody Movie movie) {
          return movieService.save(movie);
     }

     @PutMapping
     public Movie update(@RequestBody Movie movie) {
          return movieService.update(movie);
     }
}
