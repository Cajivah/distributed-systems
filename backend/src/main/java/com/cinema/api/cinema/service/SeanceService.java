package com.cinema.api.cinema.service;

import com.cinema.api.cinema.model.entity.Seance;
import com.cinema.api.cinema.repository.SeanceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SeanceService {

     private SeanceRepository seanceRepository;

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
