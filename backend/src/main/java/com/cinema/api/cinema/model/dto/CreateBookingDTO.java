package com.cinema.api.cinema.model.dto;

import com.cinema.api.cinema.model.entity.BookingOwner;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookingDTO {

     @NotNull
     private BookingOwner owner;

     @NotNull
     private Long seanceId;

     @NotNull
     private Set<Long> seatIds;
}
