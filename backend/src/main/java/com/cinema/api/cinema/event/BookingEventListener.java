package com.cinema.api.cinema.event;

import com.cinema.api.cinema.mapper.BookingConfirmationMapper;
import com.cinema.api.cinema.model.dto.BookingConfirmationMailDTO;
import com.cinema.api.cinema.model.entity.Booking;
import com.cinema.api.cinema.model.event.OnBookingCreatedEvent;
import com.cinema.api.mail.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@RequiredArgsConstructor
public class BookingEventListener {

     private final MailService mailService;
     private final BookingConfirmationMapper mapper;

     @Async
     @EventListener
     public void handleBookingCreated(OnBookingCreatedEvent event) {
          Booking booking = event.getBooking();
          Locale locale = event.getLocale();
          BookingConfirmationMailDTO mailDTO = mapper.toBookingConfirmationMailDTO(booking, locale);
          mailService.sendBookingConfirmationMail(mailDTO);
     }
}
