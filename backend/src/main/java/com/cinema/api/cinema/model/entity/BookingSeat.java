package com.cinema.api.cinema.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@NoArgsConstructor
@AllArgsConstructor
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
