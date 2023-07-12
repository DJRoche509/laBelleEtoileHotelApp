package com.djroche.labelleEtoile.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name= "reservation_status_history")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationStatusHistory {
    @Id
    @OneToOne
    @JoinColumn(name = "reservation_id", referencedColumnName = "id")
    private Reservation reservation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id", nullable = false)
    private ReservationStatus status;

    @Column(name = "date", nullable = false)
    private LocalDate date;
}
