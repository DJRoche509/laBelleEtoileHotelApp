package com.djroche.labelleEtoile.dtos;

import com.djroche.labelleEtoile.entities.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDto implements Serializable{
    private Long id;
    private String roomType;
    private boolean availability;
    private boolean ready;
    private int price;
    private boolean booked;

    public RoomDto(Room room) {
        this.id = room.getRoomId();
        this.roomType = room.getRoomType().toString(); // convert RoomType value to a String
        this.availability = room.getAvailability() != null ? room.getAvailability() : false;
        this.ready = room.isReady();
        this.price = room.getPrice();
        this.booked = room.isBooked();
    }
}
