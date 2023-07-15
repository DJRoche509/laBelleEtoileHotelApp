package com.djroche.labelleEtoile.services;

import com.djroche.labelleEtoile.dtos.ReservationStatusDto;
import com.djroche.labelleEtoile.dtos.ReservationStatusHistoryDto;
import com.djroche.labelleEtoile.entities.Reservation;
import com.djroche.labelleEtoile.entities.ReservationStatus;
import com.djroche.labelleEtoile.entities.ReservationStatusHistory;
import com.djroche.labelleEtoile.repositories.ReservationStatusHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ReservationStatusHistoryService {
    @Autowired
    private ReservationStatusHistoryRepository reservationStatusHistoryRepository;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ReservationStatusService reservationStatusService;

    public ReservationStatusHistoryDto convertToDto(ReservationStatusHistory reservationStatusHistory) {
        ReservationStatusHistoryDto reservationStatusHistoryDto = new ReservationStatusHistoryDto();
        reservationStatusHistoryDto.setReservationId(reservationStatusHistory.getReservation().getId());
        reservationStatusHistoryDto.setStatusId(reservationStatusHistory.getStatus().getId());
        reservationStatusHistoryDto.setStatusName(reservationStatusHistory.getStatusName());
        reservationStatusHistoryDto.setDate(reservationStatusHistory.getDate());
        return reservationStatusHistoryDto;
    }

    public ReservationStatusHistory convertToEntity(ReservationStatusHistoryDto reservationStatusHistoryDto) {
        ReservationStatusHistory reservationStatusHistory = new ReservationStatusHistory();
        reservationStatusHistory.setReservation(reservationService.getReservationById(reservationStatusHistoryDto.getReservationId()));
        ReservationStatusDto statusDto = reservationStatusHistoryDto.getStatus();
        ReservationStatus status = new ReservationStatus();
        status.setId(statusDto.getId());
        status.setStatus(statusDto.getStatus());
        reservationStatusHistory.setStatus(status);
        reservationStatusHistory.setDate(reservationStatusHistoryDto.getDate());
        return reservationStatusHistory;
    }

    public ReservationStatusHistoryDto createReservationStatusHistory(ReservationStatusHistoryDto reservationStatusHistoryDto) {
        ReservationStatusHistory reservationStatusHistory = convertToEntity(reservationStatusHistoryDto);
        ReservationStatusHistory savedReservationStatusHistory = reservationStatusHistoryRepository.save(reservationStatusHistory);
        return convertToDto(savedReservationStatusHistory);
    }

    public List<ReservationStatusHistoryDto> getReservationStatusHistoryByReservationId(Long reservationId) {
        List<ReservationStatusHistory> reservationStatusHistories =
                reservationStatusHistoryRepository.findByReservationId(reservationId);
        return reservationStatusHistories.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<ReservationStatusHistoryDto> getAllReservationStatusHistories() {
        List<ReservationStatusHistory> reservationStatusHistories = reservationStatusHistoryRepository.findAll();
        return reservationStatusHistories.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<ReservationStatusHistoryDto> getReservationStatusHistoryByStatusName(String statusName) {
        List<ReservationStatusHistory> reservationStatusHistories =
                reservationStatusHistoryRepository.findByStatus_Status(statusName);
        return reservationStatusHistories.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public void updateReservationStatus(Long reservationId, String statusName) {
        Reservation reservation = reservationService.getReservationById(reservationId);
        ReservationStatus status = reservationStatusService.getReservationStatusByStatus(statusName);
        ReservationStatusHistory reservationStatusHistory = new ReservationStatusHistory();
        reservationStatusHistory.setReservation(reservation);
        reservationStatusHistory.setStatus(status);
        reservationStatusHistory.setDate(LocalDate.now());
        reservationStatusHistoryRepository.save(reservationStatusHistory);
    }
}