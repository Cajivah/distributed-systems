package com.cinema.api.cinema.model.entity;

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
public class Room {

     @Id
     @GeneratedValue
     private Long id;

     @Column(nullable = false)
     private String name;

     @ManyToOne(optional = false)
     private Cinema cinema;
}
