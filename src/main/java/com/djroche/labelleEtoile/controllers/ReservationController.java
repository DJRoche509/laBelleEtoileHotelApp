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

    @GetMapping("")
    public List<ReservationDto> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @PostMapping("")
    public ResponseEntity<ReservationDto> createReservation(@RequestBody ReservationDto reservationDto) {
        ReservationDto createdReservation = reservationService.createReservation(reservationDto);
        return new ResponseEntity<>(createdReservation, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDto> getReservationById(@PathVariable Long id) {
        ReservationDto reservation = reservationService.getReservationById(id).toDto();
        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateReservation(@PathVariable Long id, @RequestBody ReservationDto reservationDto) {
        if (!id.equals(reservationDto.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        reservationService.updateReservation(reservationDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservationById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
