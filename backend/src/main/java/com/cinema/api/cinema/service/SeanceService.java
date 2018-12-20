package com.cinema.api.cinema.service;

import com.cinema.api.cinema.model.entity.Seance;
import com.cinema.api.cinema.repository.SeanceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class SeanceService {

     private SeanceRepository seanceRepository;

     public List<Seance> getByCinemaIdAndDate(long cinemaId, LocalDate date) {
          ZonedDateTime dayStart = ZonedDateTime.of(date, LocalTime.MIN, ZoneId.systemDefault());
          ZonedDateTime dayEnd = ZonedDateTime.of(date, LocalTime.MAX, ZoneId.systemDefault());
          return seanceRepository.findByCinemaIdAndDateBetween(cinemaId, dayStart, dayEnd);
     }

     public Seance save(Seance seance) {
          return seanceRepository.save(seance);
     }

     public List<Seance> getAll() {
          return seanceRepository.findAll();
     }

     public Seance getOne(Long id) {
          return seanceRepository.getOne(id);
     }
}
