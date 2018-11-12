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
@AllArgsConstructor
@NoArgsConstructor
public class Cinema {

     @Id
     @GeneratedValue
     private Long id;

     @Column(unique = true, nullable = false)
     private String name;
}
