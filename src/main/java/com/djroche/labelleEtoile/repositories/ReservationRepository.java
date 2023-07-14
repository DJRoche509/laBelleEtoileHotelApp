package com.djroche.labelleEtoile.repositories;

import com.djroche.labelleEtoile.entities.Reservation;
import com.djroche.labelleEtoile.entities.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository <Reservation, Long>{
    List<Reservation> findByStatus(ReservationStatus status);
}
