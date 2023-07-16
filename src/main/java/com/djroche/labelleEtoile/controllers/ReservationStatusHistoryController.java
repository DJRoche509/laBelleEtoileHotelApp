package com.djroche.labelleEtoile.controllers;

import com.djroche.labelleEtoile.dtos.ReservationStatusHistoryDto;
import com.djroche.labelleEtoile.services.ReservationStatusHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservation-status-history")
public class ReservationStatusHistoryController {

    @Autowired
    private ReservationStatusHistoryService reservationStatusHistoryService;

    // POST /reservation-status-history
    @PostMapping
    public ResponseEntity<ReservationStatusHistoryDto> createReservationStatusHistory(@RequestBody ReservationStatusHistoryDto reservationStatusHistoryDto) {
        ReservationStatusHistoryDto savedReservationStatusHistoryDto = reservationStatusHistoryService.createReservationStatusHistory(reservationStatusHistoryDto);
        return new ResponseEntity<>(savedReservationStatusHistoryDto, HttpStatus.CREATED);
    }

    // GET /reservation-status-history/reservation/{reservationId}
    @GetMapping("/reservation/{reservationId}")
    public ResponseEntity<List<ReservationStatusHistoryDto>> getReservationStatusHistoryByReservationId(@PathVariable Long reservationId) {
        List<ReservationStatusHistoryDto> reservationStatusHistoryDtos = reservationStatusHistoryService.getReservationStatusHistoryByReservationId(reservationId);
        if (reservationStatusHistoryDtos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reservationStatusHistoryDtos);
    }

    // GET /reservation-status-history/status/{statusName}
    @GetMapping("/status/{statusName}")
    public ResponseEntity<List<ReservationStatusHistoryDto>> getReservationStatusHistoryByStatusName(@PathVariable String statusName) {
        List<ReservationStatusHistoryDto> reservationStatusHistoryDtos = reservationStatusHistoryService.getReservationStatusHistoryByStatusName(statusName);
        if (reservationStatusHistoryDtos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reservationStatusHistoryDtos);
    }
}