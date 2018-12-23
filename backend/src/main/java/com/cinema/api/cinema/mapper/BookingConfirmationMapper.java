package com.cinema.api.cinema.mapper;

import com.cinema.api.cinema.model.dto.BookingConfirmationMailDTO;
import com.cinema.api.cinema.model.entity.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Locale;

@Mapper(componentModel = "spring")
public interface BookingConfirmationMapper {

     @Mapping(target = "address", source = "booking.owner.email")
     @Mapping(target = "token", source = "booking.bookingIdentifier")
     @Mapping(target = "seance", source = "booking.seance")
     BookingConfirmationMailDTO toBookingConfirmationMailDTO(Booking booking, Locale locale);
}

