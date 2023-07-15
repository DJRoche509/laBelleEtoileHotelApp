package com.djroche.labelleEtoile.services;

import com.djroche.labelleEtoile.dtos.CustomerDto;
import com.djroche.labelleEtoile.dtos.ReservationDto;
import com.djroche.labelleEtoile.dtos.ReservationStatusDto;
import com.djroche.labelleEtoile.entities.Customer;
import com.djroche.labelleEtoile.entities.Reservation;
import com.djroche.labelleEtoile.entities.ReservationStatus;
import com.djroche.labelleEtoile.entities.Room;
import com.djroche.labelleEtoile.repositories.CustomerRepository;
import com.djroche.labelleEtoile.repositories.ReservationRepository;
import com.djroche.labelleEtoile.repositories.ReservationStatusRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReservationStatusRepository reservationStatusRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public ReservationDto convertToDto(Reservation reservation) {
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setId(reservation.getId());
        reservationDto.setDateIn(reservation.getDateIn());
        reservationDto.setDateOut(reservation.getDateOut());
        return reservationDto;
    }

    public Reservation convertToEntity(ReservationDto reservationDto) {
        Reservation reservation = new Reservation();
        reservation.setId(reservationDto.getId());
        reservation.setDateIn(reservationDto.getDateIn());
        reservation.setDateOut(reservationDto.getDateOut());
        Room room = new Room();
        reservation.setRoom(room);
        CustomerDto customerDto = reservationDto.getCustomer();
        Customer customer = customerRepository.findById(customerDto.getId()).orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + customerDto.getId()));
        reservation.setCustomer(customer);

        // Retrieve the ReservationStatus object using the Reservation object associated with the reservation
        Reservation existingReservation = reservationRepository.findById(reservationDto.getId()).orElseThrow(() -> new EntityNotFoundException("Reservation not found with id: " + reservationDto.getId()));
        ReservationStatus status = existingReservation.getStatus();
        reservation.setStatus(status);
        return reservation;
    }


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

    public Reservation getReservationById(Long reservationId) {
        Optional<Reservation> reservation = reservationRepository.findById(reservationId);
        if (reservation.isPresent()) {
            return reservation.get();
        }
        throw new EntityNotFoundException("Reservation with id " + reservationId + " not found");
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

    public void updateReservation(ReservationDto reservationDto) {
        Reservation reservation = convertToEntity(reservationDto);
        reservationRepository.save(reservation);
    }

    public void deleteReservationById(Long id) {
        Reservation reservation = reservationRepository.findById(id).orElse(null);
        if (reservation == null) {
            return;
        }
        reservationRepository.deleteById(id);
    }
}