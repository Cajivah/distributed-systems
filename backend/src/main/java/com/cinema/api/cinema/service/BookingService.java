package com.cinema.api.cinema.service;

import com.cinema.api.cinema.model.dto.CreateBookingDTO;
import com.cinema.api.cinema.model.entity.Booking;
import com.cinema.api.cinema.model.entity.BookingSeat;
import com.cinema.api.cinema.model.entity.Price;
import com.cinema.api.cinema.model.entity.Seance;
import com.cinema.api.cinema.model.entity.Seat;
import com.cinema.api.cinema.model.entity.SeatStatus;
import com.cinema.api.cinema.model.entity.SeatsRow;
import com.cinema.api.cinema.repository.BookingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookingService {

     private final BookingRepository bookingRepository;
     private final SeanceService seanceService;

     public Booking save(CreateBookingDTO createBookingDTO) {
          final Seance seance = seanceService.getOne(createBookingDTO.getSeanceId());
          final Set<BookingSeat> bookingSeats = createBookingSeats(seance, createBookingDTO);
          final Booking booking = Booking.builder()
                                         .bookingIdentifier(UUID.randomUUID().toString())
                                         .owner(createBookingDTO.getOwner())
                                         .seance(seance)
                                         .seats(bookingSeats)
                                         .build();

          return bookingRepository.save(booking);

     }

     private Set<BookingSeat> createBookingSeats(Seance seance, CreateBookingDTO createBookingDTO) {
          final List<SeatsRow> rows = seance.getRoom().getRows();
          final Map<Long, Seat> seatsById =
               rows.stream()
                   .flatMap(row -> row.getSeats().stream())
                   .collect(Collectors.toMap(Seat::getId, Function.identity()));

          return createBookingDTO.getSeatIds()
                                 .stream()
                                 .map(seatsById::get)
                                 .map(seat -> createBookingSeat(seat, seance))
                                 .collect(Collectors.toSet());
     }

     private BookingSeat createBookingSeat(Seat seat, Seance seance) {
          final Price price = Optional.ofNullable(seance.getPrices().get(seat.getSeatCategory()))
                                      .orElseThrow(RuntimeException::new);
          return BookingSeat.builder()
                            .price(price)
                            .seat(seat)
                            .seatStatus(SeatStatus.RESERVED)
                            .build();
     }

     public List<Booking> getAll() {
          return bookingRepository.findAll();
     }
}
