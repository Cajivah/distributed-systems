package com.cinema.api.cinema.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.MapKeyEnumerated;
import java.time.ZonedDateTime;
import java.util.Map;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Seance {

     @Id
     @GeneratedValue
     private Long id;

     @Column(nullable = false)
     private ZonedDateTime start;

     @ElementCollection(fetch = FetchType.EAGER)
     @MapKeyColumn(name = "seat_category")
     @MapKeyEnumerated(EnumType.STRING)
     private Map<SeatCategory, Price> prices;

     @ManyToOne(optional = false, fetch = FetchType.EAGER)
     private Room room;

     @ManyToOne(optional = false, fetch = FetchType.EAGER)
     private Movie movie;
}
