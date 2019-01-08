package com.cinema.api.cinema.mapper;

import com.cinema.api.cinema.model.dto.BasicSeanceDTO;
import com.cinema.api.cinema.model.dto.SeanceWithSeatPrizesDTO;
import com.cinema.api.cinema.model.dto.UpdateSeanceDTO;
import com.cinema.api.cinema.model.entity.Movie;
import com.cinema.api.cinema.model.entity.Room;
import com.cinema.api.cinema.model.entity.Seance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SeanceMapper {

     BasicSeanceDTO toBasicSeance(Seance seance);

     SeanceWithSeatPrizesDTO toSeanceWithPrizes(Seance seance);

     @Mapping(target = "id", source = "updateDTO.id")
     @Mapping(target = "start", source = "updateDTO.start")
     @Mapping(target = "prices", source = "updateDTO.prices")
     @Mapping(target = "bookings", ignore = true)
     void update(@MappingTarget Seance seance, UpdateSeanceDTO updateDTO, Movie movie, Room room);
}
