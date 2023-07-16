package com.djroche.labelleEtoile.controllers;

import com.djroche.labelleEtoile.dtos.ReservationDto;
import com.djroche.labelleEtoile.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    // GET /reservations
    @GetMapping
    public List<ReservationDto> getAllReservations() {
        return reservationService.getAllReservations();
    }

    // GET /reservations/{reservationId}
    @GetMapping("/{reservationId}")
    public ResponseEntity<ReservationDto> getReservationById(@PathVariable Long reservationId) {
        ReservationDto reservationDto = new ReservationDto(reservationService.getReservationById(reservationId));
        return ResponseEntity.ok(reservationDto);
    }

    // POST /reservations
    @PostMapping
    public ResponseEntity<ReservationDto> createReservation(@RequestBody ReservationDto reservationDto) {
        ReservationDto savedReservationDto = reservationService.createReservation(reservationDto);
        return new ResponseEntity<>(savedReservationDto, HttpStatus.CREATED);
    }

    // PUT /reservations/{reservationId}
    @PutMapping("/{reservationId}")
    public ResponseEntity<Void> updateReservation(@PathVariable Long reservationId, @RequestBody ReservationDto reservationDto) {
        reservationDto.setId(reservationId);
        reservationService.updateReservation(reservationDto);
        return ResponseEntity.noContent().build();
    }

    // DELETE /reservations/{reservationId}
    @DeleteMapping("/{reservationId}")
    public ResponseEntity<Void> deleteReservationById(@PathVariable Long reservationId) {
        reservationService.deleteReservationById(reservationId);
        return ResponseEntity.noContent().build();
    }
}