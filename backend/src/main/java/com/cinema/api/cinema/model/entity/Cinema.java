package com.cinema.api.cinema.model.entity;


import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Builder
@Entity
public class Cinema {

     @Id
     @GeneratedValue
     private Long id;

     @Column(unique = true, nullable = false)
     private String name;
}
