package com.cinema.api.cinema.controller;

import com.cinema.api.cinema.model.dto.CreateBookingDTO;
import com.cinema.api.cinema.model.entity.Booking;
import com.cinema.api.cinema.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bookings")
@AllArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public Booking save(@RequestBody CreateBookingDTO createBookingDTO) {
        return bookingService.save(createBookingDTO);
    }

    @GetMapping
    public List<Booking> getAll() {
        return bookingService.getAll();
    }
}
