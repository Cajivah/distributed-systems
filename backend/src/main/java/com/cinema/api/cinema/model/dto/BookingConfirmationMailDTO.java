package com.cinema.api.cinema.model.dto;

import com.cinema.api.cinema.model.entity.BookingOwner;
import com.cinema.api.cinema.model.entity.BookingSeat;
import com.cinema.api.cinema.model.entity.Seance;
import lombok.Data;

import java.util.Locale;
import java.util.Set;

@Data
public class BookingConfirmationMailDTO {

     private String address;
     private Seance seance;
     private Set<BookingSeat> seats;
     private BookingOwner owner;
     private String token;
     private Locale locale;
}
