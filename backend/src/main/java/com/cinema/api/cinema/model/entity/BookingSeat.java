package com.cinema.api.cinema.model.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@Builder
@Entity
public class BookingSeat {

     @Id
     @GeneratedValue
     private Long id;

     @ManyToOne
     private Seat seat;

     @Enumerated(EnumType.STRING)
     private SeatStatus seatStatus;

     @Embedded
     private Price price;
}
