package com.cinema.api.cinema.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class MovieImage {

     @Id
     @GeneratedValue
     private Long id;

     @Column(nullable = false)
     private String imageUrl;
}
