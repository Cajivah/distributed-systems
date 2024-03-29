package com.cinema.api.cinema.model.dto;

import com.cinema.api.cinema.model.entity.BookingOwner;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookingDTO extends SellSeatDTO {

     @NotNull
     private BookingOwner owner;
}
