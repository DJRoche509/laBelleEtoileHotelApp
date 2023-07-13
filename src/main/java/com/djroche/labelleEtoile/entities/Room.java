package com.djroche.labelleEtoile.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name= "Rooms")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long roomId;

    @Enumerated(EnumType.STRING)
    @Column(name = "room_type")
    private RoomType roomType;

    @Column(name = "availability")
    private Boolean availability;

    @Column(name = "ready")
    private Boolean ready;

    @Column(name = "price")
    private Integer price;

    @Column(name = "booked", nullable = false)
    private Boolean booked;

    // Many-to-many relationship between Room and Reservation entities, where a room can be associated with
    // multiple reservations, and a reservation can have multiple rooms
    @ManyToMany(mappedBy = "rooms", fetch = FetchType.LAZY)
    @JsonIgnore   // used to prevent infinite recursion when serializing the entity to JSON
    private Set<Reservation> reservations = new HashSet<>();

    // isAvailable(LocalDate dateIn, LocalDate dateOut): This method checks if the room is available for the given date range.
    public boolean isAvailable(LocalDate dateIn, LocalDate dateOut) {
        for (Reservation reservation : reservations) {
            if (reservation.getDateIn().isBefore(dateOut) && reservation.getDateOut().isAfter(dateIn)) {
                return false;
            }
        }
        return true;
    }

    // getNumberOfGuests(): This method returns the number of guests that can be accommodated in the room based on its type.
    public int getNumberOfGuests() {
        return roomType.getCapacity();
    }

    // getPrice(): This method returns the price of the room per night.
    public int getPrice() {
        return roomType.getPrice();
    }
    public boolean isReady() {
        return ready != null && ready;
    }

    public boolean isBooked() {
        return booked;
    }
}
