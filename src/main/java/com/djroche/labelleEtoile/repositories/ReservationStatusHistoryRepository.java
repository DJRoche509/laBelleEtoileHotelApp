package com.djroche.labelleEtoile.repositories;

import com.djroche.labelleEtoile.entities.ReservationStatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationStatusHistoryRepository extends JpaRepository <ReservationStatusHistory, Long> {
    List<ReservationStatusHistory> findByStatus_Status(String status);

    List<ReservationStatusHistory> findByReservationId(Long reservationId);
}
