package com.cinema.api.cinema.controller;

import com.cinema.api.cinema.model.entity.Seance;
import com.cinema.api.cinema.service.SeanceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/seances")
@AllArgsConstructor
public class SeanceController {

     private final SeanceService seanceService;

     @PostMapping
     public Seance save(@RequestBody Seance seance) {
          return seanceService.save(seance);
     }

     @GetMapping
     public List<Seance> getAll() {
          return seanceService.getAll();
     }
}
