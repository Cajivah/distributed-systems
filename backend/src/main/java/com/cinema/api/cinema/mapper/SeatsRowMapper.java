package com.cinema.api.cinema.mapper;

import com.cinema.api.cinema.model.dto.SeatDTO;
import com.cinema.api.cinema.model.dto.SeatsRowDTO;
import com.cinema.api.cinema.model.entity.Seat;
import com.cinema.api.cinema.model.entity.SeatsRow;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = SeatsMapper.class)
public abstract class SeatsRowMapper {

     @Autowired
     private SeatsMapper seatsMapper;

     public abstract SeatsRowDTO toSeatsRowDTO(SeatsRow seatsRow);

     public List<SeatsRowDTO> toSeatRowDTOs(List<SeatsRow> seatsRows, List<Seat> occupiedSeats) {
          return seatsRows.stream()
                          .map(seatsRow -> {
                               SeatsRowDTO seatsRowDTO = toSeatsRowDTO(seatsRow);
                               List<SeatDTO> seatDTOs =
                                    seatsRow.getSeats()
                                            .stream()
                                            .map(seat -> seatsMapper.toSeatDTO(seat, occupiedSeats))
                                            .collect(Collectors.toList());
                               seatsRowDTO.setSeats(seatDTOs);
                               return seatsRowDTO;
                          })
                          .collect(Collectors.toList());
     }
}
