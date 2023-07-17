package com.djroche.labelleEtoile.controllers;

import com.djroche.labelleEtoile.dtos.ReservationDto;
import com.djroche.labelleEtoile.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    // GET /reservations
    @GetMapping
    public String getAllReservations(Model model) {
        model.addAttribute("reservations", reservationService.getAllReservations());
        return "reservationList";
    }

    // GET /reservations/{reservationId}
    @GetMapping("/{reservationId}")
    public String getReservationById(@PathVariable Long reservationId, Model model) {
        ReservationDto reservationDto = new ReservationDto(reservationService.getReservationById(reservationId));
        model.addAttribute("reservation", reservationDto);
        return "reservationDetails";
    }

    // GET /reservations/new
    @GetMapping("/new")
    public String showReservationForm(Model model) {
        model.addAttribute("reservationDto", new ReservationDto());
        return "reservationForm";
    }

    // POST /reservations
    @PostMapping("/")
    public String createReservation(@ModelAttribute("reservationDto") ReservationDto reservationDto) {
        reservationService.createReservation(reservationDto);
        return "redirect:/reservations";
    }

    // GET /reservations/{reservationId}/edit
    @GetMapping("/{reservationId}/edit")
    public String showEditReservationForm(@PathVariable Long reservationId, Model model) {
        ReservationDto reservationDto = new ReservationDto(reservationService.getReservationById(reservationId));
        model.addAttribute("reservationDto", reservationDto);
        return "reservationForm";
    }

    // PUT /reservations/{reservationId}
    @PutMapping("/{reservationId}")
    public String updateReservation(@PathVariable Long reservationId, @ModelAttribute("reservationDto") ReservationDto reservationDto) {
        reservationDto.setId(reservationId);
        reservationService.updateReservation(reservationDto);
        return "redirect:/reservations";
    }

    // DELETE /reservations/{reservationId}
    @DeleteMapping("/{reservationId}")
    public String deleteReservationById(@PathVariable Long reservationId) {
        reservationService.deleteReservationById(reservationId);
        return "redirect:/reservations";
    }
}