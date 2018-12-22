package com.cinema.api.cinema.model.dto;

import com.cinema.api.cinema.model.entity.Price;
import com.cinema.api.cinema.model.entity.SeatCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeanceWithSeatPrizesDTO {

     private Long id;

     private ZonedDateTime start;

     private Map<SeatCategory, Price> prices;
}
