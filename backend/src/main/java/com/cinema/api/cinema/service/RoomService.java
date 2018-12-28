package com.cinema.api.cinema.service;

import com.cinema.api.cinema.model.entity.Room;
import com.cinema.api.cinema.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoomService {

     private final RoomRepository roomRepository;

     public Room save(Room room) {
          return roomRepository.save(room);
     }

     public Page<Room> getAll(Pageable pageable) {
          return roomRepository.findAll(pageable);
     }
}
