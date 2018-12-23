package com.cinema.api.cinema.model.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

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

     @Column(nullable = false, length = 2000)
     private String description;

     @OneToMany(cascade = CascadeType.ALL)
     @JoinColumn(name = "MOVIE_ID")
     private List<MovieImage> movieImages;
}
