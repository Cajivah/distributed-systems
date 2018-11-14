package com.cinema.api.cinema.model.dto;

import com.cinema.api.cinema.model.entity.BookingOwner;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookingDTO {

     private BookingOwner owner;
     private Long seanceId;
     private Set<Long> seatIds;
}
