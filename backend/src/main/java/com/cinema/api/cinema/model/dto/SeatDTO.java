package com.cinema.api.cinema.model.dto;

import com.cinema.api.cinema.model.entity.SeatCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeatDTO {

     private Long id;

     private String seatCode;

     private SeatCategory seatCategory;

     private Boolean occupied;
}
