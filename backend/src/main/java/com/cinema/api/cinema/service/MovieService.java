package com.cinema.api.cinema.service;

import com.cinema.api.cinema.model.entity.Movie;
import com.cinema.api.cinema.repository.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

     public Page<Movie> getAll(Pageable pageable) {
          return movieRepository.findAll(pageable);
     }

     public Movie getOne(long movieId) {
          return movieRepository.getOne(movieId);
     }
}
