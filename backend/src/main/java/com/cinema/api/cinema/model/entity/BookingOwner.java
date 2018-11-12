package com.cinema.api.cinema.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingOwner {

     @Id
     @GeneratedValue
     private Long id;

     @Column(nullable = false)
     @NotNull
     private String firstName;

     @Column(nullable = false)
     @NotNull
     private String lastName;

     @Column
     private String phone;

     @Column(nullable = false)
     @NotNull
     private String email;
}
