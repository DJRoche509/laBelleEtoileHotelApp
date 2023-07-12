package com.djroche.labelleEtoile.repositories;

import com.djroche.labelleEtoile.entities.ReservationStatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationStatusHistoryRepository extends JpaRepository <ReservationStatusHistory, Long> {

}
