package com.cinema.api.cinema.service;

import com.cinema.api.cinema.model.entity.Room;
import com.cinema.api.cinema.repository.read.ReadRoomRepository;
import com.cinema.api.cinema.repository.write.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoomService {

     private final RoomRepository roomRepository;
     private final ReadRoomRepository readRoomRepository;

     public Room save(Room room) {
          room.setId(null);
          return roomRepository.save(room);
     }

     public Room update(Room room) {
          return roomRepository.save(room);
     }

     public Page<Room> getAll(Pageable pageable) {
          return readRoomRepository.findAll(pageable);
     }

     public Room getOne(long roomId) {
          return readRoomRepository.getOne(roomId);
     }
}
