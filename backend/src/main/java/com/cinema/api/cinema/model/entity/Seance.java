package com.cinema.api.cinema.model.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.ZonedDateTime;

@Data
@Builder
@Entity
public class Seance {

     @Id
     @GeneratedValue
     private Long id;

     @Column(nullable = false)
     private ZonedDateTime start;

     @Column(nullable = false)
     @Embedded
     private Price price;

     @ManyToOne(optional = false)
     private Room room;

     @ManyToOne(optional = false)
     private Movie movie;
}
