package com.cinema.api.cinema.service;

import com.cinema.api.cinema.mapper.SeanceMapper;
import com.cinema.api.cinema.model.dto.BasicSeanceDTO;
import com.cinema.api.cinema.model.dto.MovieWithSeancesDTO;
import com.cinema.api.cinema.model.entity.Movie;
import com.cinema.api.cinema.model.entity.Seance;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@Service
@RequiredArgsConstructor
public class ProgrammeService {

     private final SeanceService seanceService;
     private final SeanceMapper seanceMapper;

     public List<MovieWithSeancesDTO> getProgramme(long cinemaId, LocalDate date) {
          List<Seance> seances = seanceService.getByCinemaIdAndDate(cinemaId, date);
          Map<Movie, List<BasicSeanceDTO>> seancesGroupedByMovie
               = seances.stream()
                        .collect(groupingBy(Seance::getMovie, mapping(seanceMapper::toSimpleSeance, toList())));

          return seancesGroupedByMovie
               .entrySet()
               .stream()
               .map(entry -> {
                    Movie movie = entry.getKey();
                    return new MovieWithSeancesDTO(movie.getId(), movie.getTitle(), entry.getValue());
               })
               .collect(Collectors.toList());
     }
}
