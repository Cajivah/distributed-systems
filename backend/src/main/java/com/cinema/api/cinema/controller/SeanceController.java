package com.cinema.api.cinema.controller;

import com.cinema.api.cinema.model.dto.CreateBookingDTO;
import com.cinema.api.cinema.model.dto.CreateSeanceDTO;
import com.cinema.api.cinema.model.dto.SeanceExtendedDTO;
import com.cinema.api.cinema.model.dto.SellSeatDTO;
import com.cinema.api.cinema.model.dto.UpdateSeanceDTO;
import com.cinema.api.cinema.model.entity.Booking;
import com.cinema.api.cinema.model.entity.Seance;
import com.cinema.api.cinema.model.event.OnBookingCreatedEvent;
import com.cinema.api.cinema.service.BookingService;
import com.cinema.api.cinema.service.SeanceService;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@RestController
@RequestMapping("/seances")
@AllArgsConstructor
public class SeanceController {

     private final SeanceService seanceService;
     private final BookingService bookingService;
     private final ApplicationEventPublisher eventPublisher;

     @PostMapping
     public Seance save(@RequestBody CreateSeanceDTO seanceDTO) {
          return seanceService.save(seanceDTO);
     }

     @GetMapping
     public Page<Seance> getAll(Pageable pageable) {
          return seanceService.getAll(pageable);
     }

     @GetMapping("/cinema/{cinemaId}")
     public Page<Seance> getAll(Pageable pageable, @PathVariable String cinemaId) {
          return seanceService.getAllByCinema(cinemaId, pageable);
     }

     @GetMapping("/{id}")
     public SeanceExtendedDTO getExtendedInfo(@PathVariable Long id) {
          return seanceService.getExtendedInfo(id);
     }

     @PutMapping
     public Seance update(@RequestBody @Validated UpdateSeanceDTO seanceDTO) {
          return seanceService.update(seanceDTO);
     }

     @PostMapping("/{seanceId}/bookings")
     public Booking book(@RequestBody @Validated CreateBookingDTO createBookingDTO,
                         @PathVariable Long seanceId,
                         HttpServletRequest request) {
          Booking booking = bookingService.save(createBookingDTO, seanceId);
          Locale locale = request.getLocale();
          eventPublisher.publishEvent(new OnBookingCreatedEvent(this, booking, locale));
          return booking;
     }

     @PostMapping("/{seanceId}/sell")
     public Booking sell(@RequestBody @Validated SellSeatDTO sellSeatDTO,
                         @PathVariable Long seanceId) {
          return bookingService.save(sellSeatDTO, seanceId);
     }
}
