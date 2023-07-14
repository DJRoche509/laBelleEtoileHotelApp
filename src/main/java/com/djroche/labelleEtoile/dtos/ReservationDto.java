package com.djroche.labelleEtoile.dtos;

import com.djroche.labelleEtoile.entities.Reservation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDto implements Serializable{
    private Long id;

    private CustomerDto customer;

    private Set<RoomDto> rooms;

    private LocalDate reservationDate;

    private LocalDate dateIn;

    private LocalDate dateOut;

    private int dateRange;

    private UserDto user;
}
