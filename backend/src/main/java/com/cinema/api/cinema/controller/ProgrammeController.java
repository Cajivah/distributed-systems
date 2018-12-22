package com.cinema.api.cinema.controller;

import com.cinema.api.cinema.model.dto.ProgrammeDTO;
import com.cinema.api.cinema.service.ProgrammeService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/programme")
public class ProgrammeController {

     private final ProgrammeService programmeService;

     @GetMapping("{cinemaId}/{date}")
     public ProgrammeDTO getForCinemaAndDate(@PathVariable Long cinemaId,
                  @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
          return new ProgrammeDTO(programmeService.getProgramme(cinemaId, date));
     }
}
