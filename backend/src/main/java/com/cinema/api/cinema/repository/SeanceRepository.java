package com.cinema.api.cinema.repository;

import com.cinema.api.cinema.model.entity.Seance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface SeanceRepository extends JpaRepository<Seance, Long> {

     @Query("select seance from Seance seance where room.cinema.id = ?1 and start between ?2 and ?3")
     List<Seance> findByCinemaIdAndDateBetween(long cinemaId, ZonedDateTime start, ZonedDateTime end);
}
