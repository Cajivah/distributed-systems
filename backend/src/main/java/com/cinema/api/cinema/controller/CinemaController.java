package com.cinema.api.cinema.controller;

import com.cinema.api.cinema.model.entity.Cinema;
import com.cinema.api.cinema.service.CinemaService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cinema")
@AllArgsConstructor
public class CinemaController {

    private final CinemaService cinemaService;

    @PostMapping
    public Cinema save(@RequestBody Cinema cinema) {
        return cinemaService.save(cinema);
    }

    @GetMapping
    public List<Cinema> getAll() {
        return cinemaService.getAll();
    }
}
