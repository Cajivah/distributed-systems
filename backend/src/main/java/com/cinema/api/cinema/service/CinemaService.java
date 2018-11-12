package com.cinema.api.cinema.service;

import com.cinema.api.cinema.model.entity.Cinema;
import com.cinema.api.cinema.repository.CinemaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CinemaService {

    private final CinemaRepository cinemaRepository;

    public List<Cinema> getAll() {
        return cinemaRepository.findAll();
    }

    public Cinema save(Cinema cinema) {
        return cinemaRepository.save(cinema);
    }
}
