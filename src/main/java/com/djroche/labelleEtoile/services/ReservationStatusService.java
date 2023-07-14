package com.djroche.labelleEtoile.services;

import com.djroche.labelleEtoile.dtos.ReservationStatusDto;
import com.djroche.labelleEtoile.entities.Reservation;
import com.djroche.labelleEtoile.entities.ReservationStatus;
import com.djroche.labelleEtoile.repositories.ReservationRepository;
import com.djroche.labelleEtoile.repositories.ReservationStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ReservationStatusService {
    @Autowired
    private ReservationStatusRepository reservationStatusRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    public ReservationStatusDto createReservationStatus(ReservationStatusDto reservationStatusDto) {
        Reservation reservation = reservationRepository.findById(reservationStatusDto.getReservationId()).orElse(null);
        ReservationStatus reservationStatus = new ReservationStatus();
        reservationStatus.setStatus(reservationStatusDto.getStatus());
        reservationStatus.setReservation(reservation);
        ReservationStatus savedReservationStatus = reservationStatusRepository.save(reservationStatus);
        reservationStatusDto.setId(savedReservationStatus.getId());
        return reservationStatusDto;
    }

    public ReservationStatusDto getReservationStatusById(Long id) {
        ReservationStatus reservationStatus = reservationStatusRepository.findById(id).orElse(null);
        if (reservationStatus == null) {
            return null;
        }
        ReservationStatusDto reservationStatusDto = new ReservationStatusDto();
        reservationStatusDto.setId(reservationStatus.getId());
        reservationStatusDto.setStatus(reservationStatus.getStatus());
        reservationStatusDto.setReservationId(reservationStatus.getReservation().getId());
        return reservationStatusDto;
    }

    public List<ReservationStatusDto> getAllReservationStatuses() {
        List<ReservationStatus> reservationStatuses = reservationStatusRepository.findAll();
        List<ReservationStatusDto> reservationStatusDtos = new ArrayList<>();
        for (ReservationStatus reservationStatus : reservationStatuses) {
            ReservationStatusDto reservationStatusDto = new ReservationStatusDto();
            reservationStatusDto.setId(reservationStatus.getId());
            reservationStatusDto.setStatus(reservationStatus.getStatus());
            reservationStatusDto.setReservationId(reservationStatus.getReservation().getId());
            reservationStatusDtos.add(reservationStatusDto);
        }
        return reservationStatusDtos;
    }

    public void deleteReservationStatusById(Long id) {
        reservationStatusRepository.deleteById(id);
    }
}
