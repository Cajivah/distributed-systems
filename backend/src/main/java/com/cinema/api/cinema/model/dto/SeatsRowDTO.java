package com.cinema.api.cinema.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeatsRowDTO {

     private Long id;

     private String rowCode;

     private List<SeatDTO> seats;
}
