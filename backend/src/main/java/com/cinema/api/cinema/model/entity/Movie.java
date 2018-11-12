package com.cinema.api.cinema.model.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

     @Id
     @GeneratedValue
     private Long id;

     @Column(nullable = false)
     private Integer lengthMinutes;

     @Column(nullable = false)
     private String director;

     @Column(nullable = false)
     private String title;

     @Column(nullable = false)
     private String description;
}
