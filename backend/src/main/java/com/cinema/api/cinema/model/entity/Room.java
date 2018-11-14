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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import java.util.List;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Room {

     @Id
     @GeneratedValue
     private Long id;

     @Column(nullable = false)
     private String name;

     @ManyToOne(optional = false)
     private Cinema cinema;

     @OneToMany(cascade = CascadeType.ALL)
     @OrderColumn(name = "INDEX")
     @JoinColumn(name = "ROOM_ID")
     private List<SeatsRow> rows;
}
