package com.djroche.labelleEtoile.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "reservation_status")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationStatus {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_id")
    private Long id;

    @Column(name = "status")
    private String status;


    public boolean isActive() {
        return status.equalsIgnoreCase("active");
    }

    public boolean isCancelled() {
        return status.equalsIgnoreCase("cancelled");
    }

    public boolean isCompleted() {
        return status.equalsIgnoreCase("completed");
    }
}