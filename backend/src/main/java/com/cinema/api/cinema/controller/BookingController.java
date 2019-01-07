package com.cinema.api.cinema.controller;

import com.cinema.api.cinema.model.entity.Booking;
import com.cinema.api.cinema.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bookings")
@AllArgsConstructor
public class BookingController {

     private final BookingService bookingService;

     @GetMapping
     public List<Booking> getAll() {
          return bookingService.getAll();
     }

     @GetMapping
     @RequestMapping("/{identifier}")
     public Booking getByIdentifier(@PathVariable String identifier) {
          return bookingService.getByIdentifier(identifier);
     }

     @DeleteMapping
     @ResponseStatus(HttpStatus.NO_CONTENT)
     public void delete(@RequestParam String identifier) {
          bookingService.delete(identifier);
     }
}
