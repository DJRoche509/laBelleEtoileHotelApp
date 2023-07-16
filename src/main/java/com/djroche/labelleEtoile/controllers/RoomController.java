package com.djroche.labelleEtoile.controllers;

import com.djroche.labelleEtoile.dtos.RoomDto;
import com.djroche.labelleEtoile.entities.RoomType;
import com.djroche.labelleEtoile.services.RoomService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping("/")
    public RoomDto createRoom(@RequestBody RoomDto roomDto) {
        RoomDto createdRoom = roomService.createRoom(roomDto);
        return createdRoom;
    }

    @GetMapping("/{id}")
    public RoomDto getRoomById(@PathVariable Long id) {
        RoomDto roomDto = roomService.getRoomById(id);
        return roomDto;
    }

    @GetMapping("/")
    public List<RoomDto> getAllRooms() {
        List<RoomDto> rooms = roomService.getAllRooms();
        return rooms;
    }

    @GetMapping("/{roomId}/availability")
    public boolean isRoomAvailable(@PathVariable Long roomId, @RequestParam("dateIn") String dateIn, @RequestParam("dateOut") String dateOut) {
        LocalDate dateInParsed = LocalDate.parse(dateIn);
        LocalDate dateOutParsed = LocalDate.parse(dateOut);
        return roomService.isRoomAvailable(roomId, dateInParsed, dateOutParsed);
    }

    @GetMapping("/{roomId}/ready")
    public boolean isRoomReady(@PathVariable Long roomId) {
        return roomService.isRoomReady(roomId);
    }

    @GetMapping("/{roomId}/booked")
    public boolean isRoomBooked(@PathVariable Long roomId) {
        return roomService.isRoomBooked(roomId);
    }

    @GetMapping("/{roomId}/price")
    public int getRoomPrice(@PathVariable Long roomId) {
        return roomService.getRoomPrice(roomId);
    }

    @GetMapping("/numGuests")
    public int getNumberOfGuests(@RequestParam("roomType") String roomType) {
        RoomType roomTypeEnum = RoomType.valueOf(roomType);
        return roomService.getNumberOfGuests(roomTypeEnum);
    }
}