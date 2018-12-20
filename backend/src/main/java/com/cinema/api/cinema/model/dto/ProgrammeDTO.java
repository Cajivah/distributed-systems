package com.cinema.api.cinema.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ProgrammeDTO {

    private List<MovieWithSeancesDTO> programme;
}
