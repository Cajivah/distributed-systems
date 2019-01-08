package com.cinema.api.cinema.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.MapKeyEnumerated;
import javax.persistence.OneToMany;
import java.time.ZonedDateTime;
import java.util.List;
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

     @ElementCollection
     @MapKeyColumn(name = "seat_category")
     @MapKeyEnumerated(EnumType.STRING)
     private Map<SeatCategory, Price> prices;

     @ManyToOne(optional = false)
     private Room room;

     @ManyToOne(optional = false)
     private Movie movie;

     @JsonIgnore
     @OneToMany(cascade = CascadeType.ALL)
     @JoinColumn(name = "SEANCE_ID")
     private List<Booking> bookings;
}
