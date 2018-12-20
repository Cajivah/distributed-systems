package com.cinema.api.cinema.model.dto;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class BasicSeanceDTO {

     private long id;

     private ZonedDateTime start;
}
