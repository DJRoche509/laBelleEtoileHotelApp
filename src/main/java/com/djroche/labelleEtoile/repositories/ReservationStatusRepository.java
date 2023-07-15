package com.djroche.labelleEtoile.repositories;

import com.djroche.labelleEtoile.entities.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationStatusRepository extends JpaRepository <ReservationStatus, Long> {
    ReservationStatus findByStatus(String status);
}
