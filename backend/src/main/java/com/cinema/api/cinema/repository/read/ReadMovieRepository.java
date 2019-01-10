package com.cinema.api.cinema.repository.read;

import com.cinema.api.cinema.model.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReadMovieRepository extends JpaRepository<Movie, Long> {
}

