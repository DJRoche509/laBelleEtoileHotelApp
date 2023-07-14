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
}
