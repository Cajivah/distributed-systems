package com.cinema.api.cinema.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import java.util.List;


@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "seats")
public class SeatsRow {

     @Id
     @GeneratedValue
     private Long id;

     @Column(nullable = false)
     private String rowCode;

     @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
     @OrderColumn(name = "INDEX")
     @JoinColumn(name = "SEATS_ROW_ID")
     private List<Seat> seats;
}
