package com.cinema.api.cinema.repository.write;

import com.cinema.api.cinema.model.entity.BookingOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingOwnerRepository extends JpaRepository<BookingOwner, Long> {

     BookingOwner getByEmail(String email);
}
