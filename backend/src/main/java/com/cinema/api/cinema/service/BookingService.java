package com.cinema.api.cinema.service;

import com.cinema.api.cinema.model.dto.CreateBookingDTO;
import com.cinema.api.cinema.model.dto.SellSeatDTO;
import com.cinema.api.cinema.model.entity.Booking;
import com.cinema.api.cinema.model.entity.BookingOwner;
import com.cinema.api.cinema.model.entity.BookingSeat;
import com.cinema.api.cinema.model.entity.Price;
import com.cinema.api.cinema.model.entity.Seance;
import com.cinema.api.cinema.model.entity.Seat;
import com.cinema.api.cinema.model.entity.SeatStatus;
import com.cinema.api.cinema.model.entity.SeatsRow;
import com.cinema.api.cinema.repository.BookingOwnerRepository;
import com.cinema.api.cinema.repository.BookingRepository;
import com.cinema.api.mail.config.properties.MailExtendedProperties;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@EnableConfigurationProperties(MailExtendedProperties.class)
public class BookingService {

     private final BookingRepository bookingRepository;
     private final SeanceService seanceService;
     private final BookingOwnerRepository bookingOwnerRepository;
     private final MailExtendedProperties mailExtendedProperties;

     public Booking save(CreateBookingDTO createBookingDTO, Long seanceId) {
          BookingOwner owner = createBookingDTO.getOwner();
          return createAndSaveBooking(createBookingDTO, owner, SeatStatus.RESERVED, seanceId);
     }

     public Booking save(SellSeatDTO sellSeatDTO, Long seanceId) {
          BookingOwner owner = bookingOwnerRepository.getByEmail(mailExtendedProperties.getNoReplyAddress());
          return createAndSaveBooking(sellSeatDTO, owner, SeatStatus.SOLD, seanceId);
     }

     private Booking createAndSaveBooking(SellSeatDTO sellSeatDTO, BookingOwner owner,
                                          SeatStatus seatStatus, Long seanceId) {
          final Seance seance = seanceService.getOne(seanceId);
          final Set<BookingSeat> bookingSeats = createBookingSeats(seance, sellSeatDTO, seatStatus);
          final Booking booking = Booking.builder()
                                         .bookingIdentifier(UUID.randomUUID().toString())
                                         .owner(owner)
                                         .seance(seance)
                                         .seats(bookingSeats)
                                         .build();

          return bookingRepository.save(booking);
     }

     private Set<BookingSeat> createBookingSeats(Seance seance, SellSeatDTO sellSeatDTO, SeatStatus seatStatus) {
          final List<SeatsRow> rows = seance.getRoom().getRows();
          final Map<Long, Seat> seatsById =
               rows.stream()
                   .flatMap(row -> row.getSeats().stream())
                   .collect(Collectors.toMap(Seat::getId, Function.identity()));

          return sellSeatDTO.getSeatIds()
                            .stream()
                            .map(seatsById::get)
                            .map(seat -> createBookingSeat(seat, seance, seatStatus))
                            .collect(Collectors.toSet());
     }

     private BookingSeat createBookingSeat(Seat seat, Seance seance, SeatStatus seatStatus) {
          final Price price = Optional.ofNullable(seance.getPrices().get(seat.getSeatCategory()))
                                      .orElseThrow(RuntimeException::new);
          return BookingSeat.builder()
                            .price(price)
                            .seat(seat)
                            .seatStatus(seatStatus)
                            .build();
     }

     public List<Booking> getAll() {
          return bookingRepository.findAll();
     }

     @Transactional
     public void delete(String identifier) {
          bookingRepository.deleteByBookingIdentifier(identifier);
     }

     public Booking getByIdentifier(String identifier) {
          return bookingRepository.getByBookingIdentifier(identifier);
     }
}
