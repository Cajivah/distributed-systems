package com.cinema.api.cinema.controller;

import com.cinema.api.cinema.model.dto.CreateBookingDTO;
import com.cinema.api.cinema.model.entity.Booking;
import com.cinema.api.cinema.model.event.OnBookingCreatedEvent;
import com.cinema.api.cinema.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/bookings")
@AllArgsConstructor
public class BookingController {

    private final BookingService bookingService;
    private final ApplicationEventPublisher eventPublisher;

    @PostMapping
    public Booking save(@RequestBody @Validated CreateBookingDTO createBookingDTO,
                        HttpServletRequest request) {
         Booking booking = bookingService.save(createBookingDTO);
         Locale locale = request.getLocale();
         eventPublisher.publishEvent(new OnBookingCreatedEvent(this, booking, locale));
         return booking;
    }

    @GetMapping
    public List<Booking> getAll() {
        return bookingService.getAll();
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestParam String identifier) {
         bookingService.delete(identifier);
    }
}
