package com.cinema.api.cinema.service;

import com.cinema.api.cinema.model.entity.Cinema;
import com.cinema.api.cinema.repository.read.ReadCinemaRepository;
import com.cinema.api.cinema.repository.write.CinemaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CinemaService {

    private final CinemaRepository cinemaRepository;
    private final ReadCinemaRepository readCinemaRepository;

    public List<Cinema> getAll() {
        return readCinemaRepository.findAll();
    }

    public Cinema save(Cinema cinema) {
        return cinemaRepository.save(cinema);
    }
}
