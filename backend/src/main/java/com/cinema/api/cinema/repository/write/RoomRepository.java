package com.cinema.api.cinema.repository.write;

import com.cinema.api.cinema.model.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    Page<Room> findAllByCinema_Id(Long cinemaId, Pageable pageable);
}
