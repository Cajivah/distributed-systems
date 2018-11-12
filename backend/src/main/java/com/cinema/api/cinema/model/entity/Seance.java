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
import java.time.ZonedDateTime;
import java.util.Set;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Seance {

     @Id
     @GeneratedValue
     private Long id;

     @Column(nullable = false)
     private ZonedDateTime start;

     @OneToMany(cascade = CascadeType.ALL)
     @JoinColumn(name = "SEANCE_ID")
     private Set<CategoryPrice> prices;

     @ManyToOne(optional = false)
     private Room room;

     @ManyToOne(optional = false)
     private Movie movie;
}
