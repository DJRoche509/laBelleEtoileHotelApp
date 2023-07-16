package com.djroche.labelleEtoile.controllers;

import com.djroche.labelleEtoile.dtos.ReservationStatusDto;
import com.djroche.labelleEtoile.services.ReservationStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservation-statuses")
public class ReservationStatusController {

    @Autowired
    private ReservationStatusService reservationStatusService;

    // GET /reservation-statuses
    @GetMapping
    public List<ReservationStatusDto> getAllReservationStatuses() {
        return reservationStatusService.getAllReservationStatuses();
    }

    // GET /reservation-statuses/{reservationStatusId}
    @GetMapping("/{reservationStatusId}")
    public ResponseEntity<ReservationStatusDto> getReservationStatusById(@PathVariable Long reservationStatusId) {
        ReservationStatusDto reservationStatusDto = reservationStatusService.getReservationStatusById(reservationStatusId);
        if (reservationStatusDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reservationStatusDto);
    }

    // POST /reservation-statuses
    @PostMapping
    public ResponseEntity<ReservationStatusDto> createReservationStatus(@RequestBody ReservationStatusDto reservationStatusDto) {
        ReservationStatusDto savedReservationStatusDto = reservationStatusService.createReservationStatus(reservationStatusDto);
        return new ResponseEntity<>(savedReservationStatusDto, HttpStatus.CREATED);
    }

    // DELETE /reservation-statuses/{reservationStatusId}
    @DeleteMapping("/{reservationStatusId}")
    public ResponseEntity<Void> deleteReservationStatusById(@PathVariable Long reservationStatusId) {
        reservationStatusService.deleteReservationStatusById(reservationStatusId);
        return ResponseEntity.noContent().build();   // returns a HTTP 204 No Content response
    }
}