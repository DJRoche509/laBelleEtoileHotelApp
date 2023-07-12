package com.djroche.labelleEtoile.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Column(name = "room_type")
    private String roomType;

    @Column(name = "availability")
    private Boolean availability;

    @Column(name = "ready")
    private Boolean ready;

    @Column(name = "price")
    private Integer price;

    @Column(name = "booked")
    private Boolean booked;
}
