package com.cinema.api.cinema.mapper;

import com.cinema.api.cinema.model.dto.SeatDTO;
import com.cinema.api.cinema.model.entity.Seat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SeatsMapper {

     @Mapping(target = "occupied", ignore = true)
     SeatDTO toSeatDTO(Seat seat);

     default SeatDTO toSeatDTO(Seat seat, List<Seat> occupiedSeats) {
          return occupiedSeats.contains(seat)
               ? toSeatDTO(seat, true)
               : toSeatDTO(seat, false);
     }

     SeatDTO toSeatDTO(Seat seat, Boolean occupied);
}
