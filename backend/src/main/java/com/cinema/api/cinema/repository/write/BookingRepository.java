package com.cinema.api.cinema.repository.write;

import com.cinema.api.cinema.model.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    void deleteByBookingIdentifier(String identifier);
}
