package com.djroche.labelleEtoile.services;

import com.djroche.labelleEtoile.dtos.RoomDto;
import com.djroche.labelleEtoile.entities.Room;
import com.djroche.labelleEtoile.entities.RoomType;
import com.djroche.labelleEtoile.repositories.RoomRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


/*
* This class separates the conversion methods from the business logic methods, using Java streams for the
* getAllRooms() method, and throwing an exception when an invalid room ID is provided.
* */
@Service
@Transactional
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

    public RoomDto convertToDto(Room room) {
        RoomDto roomDto = new RoomDto();
        roomDto.setId(room.getRoomId());
        roomDto.setRoomType(room.getRoomType().toString());
        roomDto.setAvailability(room.getAvailability());
        roomDto.setReady(room.isReady());
        roomDto.setPrice(room.getPrice());
        roomDto.setBooked(room.isBooked());
        return roomDto;
    }

    public Room convertToEntity(RoomDto roomDto) {
        Room room = new Room();
        room.setRoomType(RoomType.valueOf(roomDto.getRoomType()));
        room.setAvailability(roomDto.isAvailability());
        room.setReady(roomDto.isReady());
        room.setPrice(roomDto.getPrice());
        room.setBooked(roomDto.isBooked());
        return room;
    }

    public RoomDto createRoom(RoomDto roomDto) {
        Room room = convertToEntity(roomDto);
        Room savedRoom = roomRepository.save(room);
        return convertToDto(savedRoom);
    }

    public RoomDto getRoomById(Long id) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid room ID"));
        return convertToDto(room);
    }

    public List<RoomDto> getAllRooms() {
        List<Room> rooms = roomRepository.findAll();
        return rooms.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public boolean isRoomAvailable(Long roomId, LocalDate dateIn, LocalDate dateOut) {
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new IllegalArgumentException("Invalid room ID"));
        return room.isAvailable(dateIn, dateOut);
    }

    public boolean isRoomReady(Long roomId) {
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new IllegalArgumentException("Invalid room ID"));
        return room.isReady();
    }

    public boolean isRoomBooked(Long roomId) {
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new IllegalArgumentException("Invalid room ID"));
        return room.isBooked();
    }

    public int getRoomPrice(Long roomId) {
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new IllegalArgumentException("Invalid room ID"));
        return room.getPrice();
    }

    public int getNumberOfGuests(RoomType roomType) {
        return roomType.getCapacity();
    }
}
