package com.cinema.api.cinema.service;

import com.cinema.api.cinema.model.entity.Room;
import com.cinema.api.cinema.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoomService {

     private final RoomRepository roomRepository;

     public Room save(Room room) {
          room.setId(null);
          return roomRepository.save(room);
     }

     public Room update(Room room) {
          return roomRepository.save(room);
     }

     public List<Room> getAll() {
          return roomRepository.findAll();
     }

     public Room getOne(long roomId) {
          return roomRepository.getOne(roomId);
     }
}
