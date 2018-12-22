package com.cinema.api.cinema.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieWithSeancesDTO {

     private long movieId;

     private String movieTitle;

     private List<BasicSeanceDTO> seances;
}
