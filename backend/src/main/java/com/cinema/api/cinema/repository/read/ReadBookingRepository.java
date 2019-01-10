package com.cinema.api.cinema.repository.read;

import com.cinema.api.cinema.model.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReadBookingRepository extends JpaRepository<Booking, Long> {

     Booking getByBookingIdentifier(String identifier);
}
