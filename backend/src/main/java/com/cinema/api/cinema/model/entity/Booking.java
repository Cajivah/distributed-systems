package com.cinema.api.cinema.model.entity;


import com.cinema.api.user.model.entity.UserCredentials;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Data
@Builder
@Entity
public class Booking {

     @Id
     @GeneratedValue
     private Long id;

     @Column(nullable = false)
     private String owner;

     @Column(nullable = false, unique = true)
     private String bookingIdentifier;

     @ManyToOne(optional = false)
     private Seance seance;

     @ManyToOne(optional = false)
     private UserCredentials createdBy;
}
