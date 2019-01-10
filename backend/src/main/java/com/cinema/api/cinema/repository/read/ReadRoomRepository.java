package com.cinema.api.cinema.repository.read;

import com.cinema.api.cinema.model.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReadRoomRepository extends JpaRepository<Room, Long> {
}

