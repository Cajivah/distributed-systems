package com.cinema.api.cinema.model.dto;

import com.cinema.api.cinema.model.entity.Price;
import com.cinema.api.cinema.model.entity.SeatCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateSeanceDTO {

     @NotNull
     private Long id;

     @NotNull
     private ZonedDateTime start;

     @NotNull
     private Map<SeatCategory, Price> prices;

     @NotNull
     private Long roomId;

     @NotNull
     private Long movieId;
}
