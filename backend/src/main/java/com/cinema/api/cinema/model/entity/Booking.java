package com.cinema.api.cinema.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Set;


@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Booking {

     @Id
     @GeneratedValue
     private Long id;

     @OneToOne(cascade = CascadeType.ALL)
     private BookingOwner owner;

     @Column(nullable = false, unique = true)
     private String bookingIdentifier;

     @ManyToOne(optional = false)
     private Seance seance;

     @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
     @JoinColumn(name = "BOOKING_ID")
     private Set<BookingSeat> seats;
}
