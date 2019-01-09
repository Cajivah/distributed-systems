package com.cinema.api.cinema.service;

import com.cinema.api.cinema.mapper.SeanceMapper;
import com.cinema.api.cinema.mapper.SeatsRowMapper;
import com.cinema.api.cinema.model.dto.SeanceExtendedDTO;
import com.cinema.api.cinema.model.dto.SeanceWithSeatPrizesDTO;
import com.cinema.api.cinema.model.dto.SeatsRowDTO;
import com.cinema.api.cinema.model.dto.UpdateSeanceDTO;
import com.cinema.api.cinema.model.entity.Booking;
import com.cinema.api.cinema.model.entity.BookingSeat;
import com.cinema.api.cinema.model.entity.Movie;
import com.cinema.api.cinema.model.entity.Room;
import com.cinema.api.cinema.model.entity.Seance;
import com.cinema.api.cinema.model.entity.Seat;
import com.cinema.api.cinema.repository.write.SeanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SeanceService {

     private final SeanceRepository seanceRepository;
     private final SeanceMapper seanceMapper;
     private final SeatsRowMapper seatsRowMapper;
     private final RoomService roomService;
     private final MovieService movieService;

     public List<Seance> getByCinemaIdAndDate(long cinemaId, LocalDate date) {
          return seanceRepository.findByCinemaIdAndDate(cinemaId, date);
     }

     public Seance save(Seance seance) {
          seance.setId(null);
          return seanceRepository.save(seance);
     }

     public Seance update(UpdateSeanceDTO seanceDTO) {
          Movie movie = movieService.getOne(seanceDTO.getMovieId());
          Room room = roomService.getOne(seanceDTO.getRoomId());
          Seance seance = seanceRepository.getOne(seanceDTO.getId());
          seanceMapper.update(seance, seanceDTO, movie, room);
          return seanceRepository.save(seance);
     }

     public Page<Seance> getAll(Pageable pageable) {
          return seanceRepository.findAll(pageable);
     }

     public Seance getOne(Long id) {
          return seanceRepository.getOne(id);
     }

     public SeanceExtendedDTO getExtendedInfo(long id) {
          Seance seance = seanceRepository.getOne(id);
          Room room = seance.getRoom();
          SeanceWithSeatPrizesDTO seanceDTO = seanceMapper.toSeanceWithPrizes(seance);
          List<Seat> occupiedSeats = getOccupiedSeats(seance);
          List<SeatsRowDTO> seatRowDTOs = seatsRowMapper.toSeatRowDTOs(room.getRows(), occupiedSeats);
          return new SeanceExtendedDTO(seanceDTO, seance.getMovie(), seatRowDTOs);
     }

     public List<Seat> getOccupiedSeats(Seance seance) {
          return seance.getBookings()
                       .stream()
                       .map(Booking::getSeats)
                       .flatMap(Collection::stream)
                       .map(BookingSeat::getSeat)
                       .collect(Collectors.toList());
     }
}
