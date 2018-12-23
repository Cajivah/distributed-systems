package com.cinema.api.cinema.mapper;

import com.cinema.api.cinema.model.dto.BasicSeanceDTO;
import com.cinema.api.cinema.model.dto.SeanceWithSeatPrizesDTO;
import com.cinema.api.cinema.model.entity.Seance;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SeanceMapper {

     BasicSeanceDTO toBasicSeance(Seance seance);

     SeanceWithSeatPrizesDTO toSeanceWithPrizes(Seance seance);
}
