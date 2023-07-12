package com.djroche.labelleEtoile.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name= "Reservations")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @Column(name = "reservation_date")
    private LocalDate reservationDate;

    @Column(name = "date_in")
    private LocalDate dateIn;

    @Column(name = "date_out")
    private LocalDate dateOut;

    @Column(name = "data_range")
    private Integer dataRange;
}
