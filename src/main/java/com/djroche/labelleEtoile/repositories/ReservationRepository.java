package com.djroche.labelleEtoile.repositories;

import com.djroche.labelleEtoile.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository <Reservation, Long>{

}
