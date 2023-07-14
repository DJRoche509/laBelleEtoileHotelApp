package com.djroche.labelleEtoile.dtos;

import com.djroche.labelleEtoile.entities.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationStatusDto implements Serializable{
    private Long id;
    private Long reservationId;
    private String status;

//    public ReservationStatusDto(ReservationStatus reservationStatus) {
//        this.id = reservationStatus.getId();
//        this.status = reservationStatus.getStatus();
//    }
}
