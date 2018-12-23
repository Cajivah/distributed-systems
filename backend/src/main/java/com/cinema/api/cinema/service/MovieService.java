package com.cinema.api.cinema.service;

import com.cinema.api.cinema.model.entity.Movie;
import com.cinema.api.cinema.repository.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MovieService {

     private final MovieRepository movieRepository;

     public Movie save(Movie movie) {
          movie.setId(null);
          return movieRepository.save(movie);
     }

     public Movie update(Movie movie) {
          return movieRepository.save(movie);
     }

     public List<Movie> getAll() {
          return movieRepository.findAll();
     }

     public Optional<Movie> getOne(long movieId) {
          return movieRepository.findById(movieId);
     }
}
