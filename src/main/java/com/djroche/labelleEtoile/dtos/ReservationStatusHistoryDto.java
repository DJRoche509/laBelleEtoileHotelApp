package com.djroche.labelleEtoile.dtos;

import com.djroche.labelleEtoile.entities.ReservationStatusHistory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationStatusHistoryDto implements Serializable {
    private Long id;

    private ReservationDto reservation;

    private ReservationStatusDto status;

    private LocalDate date;

    public void setReservationId(Long reservationId) {
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setId(reservationId);
        this.reservation = reservationDto;
    }

    public void setStatusName(String statusName) {
        ReservationStatusDto reservationStatusDto = new ReservationStatusDto();
        reservationStatusDto.setStatus(statusName);
        this.status = reservationStatusDto;
    }

    public void setStatusId(Long statusId) {
        ReservationStatusDto reservationStatusDto = new ReservationStatusDto();
        reservationStatusDto.setId(statusId);
        this.status = reservationStatusDto;
    }

    public Long getReservationId() {
        return reservation.getId();
    }

    public Long getStatusId() {
        return status.getId();
    }
}
