package com.cinema.api.cinema.model.entity;

import jdk.jfr.Enabled;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@Builder
@Entity
public class BookingSeat {

     @Id
     @ManyToOne(optional = false)
     private Seat seat;

     @Id
     @ManyToOne(optional = false)
     private Booking booking;

     @Column(nullable = false)
     @Embedded
     private Price price;
}
