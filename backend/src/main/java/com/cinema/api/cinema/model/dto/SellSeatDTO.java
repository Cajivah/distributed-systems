package com.cinema.api.cinema.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SellSeatDTO {

     @NotNull
     private Set<Long> seatIds;
}
