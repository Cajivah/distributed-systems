package com.cinema.api.cinema.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Seat {

     @Id
     @GeneratedValue
     private Long id;

     @Column(nullable = false)
     private String seatCode;

     @Enumerated(EnumType.STRING)
     private SeatCategory seatCategory;

     @ManyToOne
     private SeatsRow row;
}
