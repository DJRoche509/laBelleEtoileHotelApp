package com.djroche.labelleEtoile.dtos;

import com.djroche.labelleEtoile.entities.Reservation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

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

    public ReservationDto(Reservation reservation) {
        this.id = reservation.getId();
        this.customer = new CustomerDto(reservation.getCustomer());
        this.rooms = reservation.getRooms().stream().map(RoomDto::new).collect(Collectors.toSet());
        this.reservationDate = reservation.getReservationDate();
        this.dateIn = reservation.getDateIn();
        this.dateOut = reservation.getDateOut();
        this.dateRange = reservation.getDateRange();
        this.user = new UserDto(reservation.getUser());
    }
}
