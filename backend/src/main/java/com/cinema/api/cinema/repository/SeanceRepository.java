package com.cinema.api.cinema.repository;

import com.cinema.api.cinema.model.entity.Seance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface SeanceRepository extends JpaRepository<Seance, Long> {

     default List<Seance> findByCinemaIdAndDate(long cinemaId, LocalDate date) {
          ZonedDateTime dayStart = ZonedDateTime.of(date, LocalTime.MIN, ZoneId.systemDefault());
          ZonedDateTime dayEnd = ZonedDateTime.of(date, LocalTime.MAX, ZoneId.systemDefault());
          return findByCinemaIdAndDateBetween(cinemaId, dayStart, dayEnd);
     }

     @Query("select seance from Seance seance where room.cinema.id = ?1 and start between ?2 and ?3")
     List<Seance> findByCinemaIdAndDateBetween(long cinemaId, ZonedDateTime start, ZonedDateTime end);

    Page<Seance> findAllByRoom_Cinema_Id(String cinemaId, Pageable pageable);
}
