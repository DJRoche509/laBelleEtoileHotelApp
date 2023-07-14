package com.djroche.labelleEtoile.services;

import com.djroche.labelleEtoile.dtos.RoomDto;
import com.djroche.labelleEtoile.entities.Room;
import com.djroche.labelleEtoile.entities.RoomType;
import com.djroche.labelleEtoile.repositories.RoomRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

    public RoomDto createRoom(RoomDto roomDto) {
        Room room = new Room();
        room.setRoomType(RoomType.valueOf(roomDto.getRoomType()));
        room.setAvailability(roomDto.isAvailability());
        room.setReady(roomDto.isReady());
        room.setPrice(roomDto.getPrice());
        room.setBooked(roomDto.isBooked());
        Room savedRoom = roomRepository.save(room);
        roomDto.setId(savedRoom.getRoomId());
        return roomDto;
    }

    public RoomDto getRoomById(Long id) {
        Room room = roomRepository.findById(id).orElse(null);
        if (room == null) {
            return null;
        }
        RoomDto roomDto = new RoomDto();
        roomDto.setId(room.getRoomId());
        roomDto.setRoomType(room.getRoomType().toString());
        roomDto.setAvailability(room.getAvailability());
        roomDto.setReady(room.isReady());
        roomDto.setPrice(room.getPrice());
        roomDto.setBooked(room.isBooked());
        return roomDto;
    }

    public List<RoomDto> getAllRooms() {
        List<Room> rooms = roomRepository.findAll();
        List<RoomDto> roomDtos = new ArrayList<>();
        for (Room room : rooms) {
            RoomDto roomDto = new RoomDto();
            roomDto.setId(room.getRoomId());
            roomDto.setRoomType(room.getRoomType().toString());
            roomDto.setAvailability(room.getAvailability());
            roomDto.setReady(room.isReady());
            roomDto.setPrice(room.getPrice());
            roomDto.setBooked(room.isBooked());
            roomDtos.add(roomDto);
        }
        return roomDtos;
    }
}
