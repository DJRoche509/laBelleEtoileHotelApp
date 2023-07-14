package com.djroche.labelleEtoile.services;

import com.djroche.labelleEtoile.dtos.CustomerDto;
import com.djroche.labelleEtoile.dtos.ReservationDto;
import com.djroche.labelleEtoile.entities.Customer;
import com.djroche.labelleEtoile.entities.Reservation;
import com.djroche.labelleEtoile.entities.ReservationStatus;
import com.djroche.labelleEtoile.repositories.CustomerRepository;
import com.djroche.labelleEtoile.repositories.ReservationRepository;
import com.djroche.labelleEtoile.repositories.ReservationStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReservationStatusRepository reservationStatusRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public ReservationDto createReservation(ReservationDto reservationDto) {
        Customer customer = customerRepository.findById(reservationDto.getCustomer().getId()).orElse(null);
        Reservation reservation = new Reservation();
        reservation.setCustomer(customer);
        reservation.setDateIn(reservationDto.getDateIn());
        reservation.setDateOut(reservationDto.getDateOut());
        Reservation savedReservation = reservationRepository.save(reservation);

        ReservationStatus reservationStatus = new ReservationStatus();
        reservationStatus.setReservation(savedReservation);
        reservationStatus.setStatus("active");
        reservationStatusRepository.save(reservationStatus);

        reservationDto.setId(savedReservation.getId());
        return reservationDto;
    }

    public ReservationDto getReservationById(Long id) {
        Reservation reservation = reservationRepository.findById(id).orElse(null);
        if (reservation == null) {
            return null;
        }
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setId(reservation.getId());
        reservationDto.setCustomer(reservation.getCustomer().toDto());
        reservationDto.setDateIn(reservation.getDateIn());
        reservationDto.setDateOut(reservation.getDateOut());
        return reservationDto;
    }

    public List<ReservationDto> getAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        List<ReservationDto> reservationDtos = new ArrayList<>();
        for (Reservation reservation : reservations) {
            ReservationDto reservationDto = new ReservationDto();
            reservationDto.setId(reservation.getId());
            reservationDto.setCustomer(reservation.getCustomer().toDto());
            reservationDto.setDateIn(reservation.getDateIn());
            reservationDto.setDateOut(reservation.getDateOut());
            reservationDtos.add(reservationDto);
        }
        return reservationDtos;
    }

    public void deleteReservationById(Long id) {
        Reservation reservation = reservationRepository.findById(id).orElse(null);
        if (reservation == null) {
            return;
        }
        reservationRepository.deleteById(id);
    }
}