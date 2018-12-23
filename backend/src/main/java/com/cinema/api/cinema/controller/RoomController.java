package com.cinema.api.cinema.controller;

import com.cinema.api.cinema.model.entity.Room;
import com.cinema.api.cinema.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rooms")
@AllArgsConstructor
public class RoomController {

     private final RoomService roomService;

     @GetMapping
     public List<Room> getAll() {
          return roomService.getAll();
     }

     @PostMapping
     public Room save(@RequestBody Room room) {
          return roomService.save(room);
     }

     @PutMapping
     public Room update(@RequestBody Room room) {
          return roomService.update(room);
     }
}
