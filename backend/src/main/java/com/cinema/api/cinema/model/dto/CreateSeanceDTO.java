package com.cinema.api.cinema.model.dto;

import com.cinema.api.cinema.model.entity.Price;
import com.cinema.api.cinema.model.entity.SeatCategory;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.Map;

@Data
public class CreateSeanceDTO {

     @NotNull
     private ZonedDateTime start;

     @NotNull
     private Map<SeatCategory, Price> prices;

     @NotNull
     private Long roomId;

     @NotNull
     private Long movieId;
}
