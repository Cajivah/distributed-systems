package com.cinema.api.cinema.model.dto;

import com.cinema.api.cinema.model.entity.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeanceExtendedDTO {

     private SeanceWithSeatPrizesDTO seance;

     private Movie movie;

     private List<SeatsRowDTO> rows;
}
