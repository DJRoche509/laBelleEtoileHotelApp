package com.djroche.labelleEtoile.services;

import com.djroche.labelleEtoile.dtos.CustomerDto;
import com.djroche.labelleEtoile.dtos.ReservationDto;
import com.djroche.labelleEtoile.dtos.RoomDto;
import com.djroche.labelleEtoile.dtos.UserDto;
import com.djroche.labelleEtoile.entities.*;
import com.djroche.labelleEtoile.repositories.ReservationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private UserService userService;

    @Autowired
    private ReservationStatusService reservationStatusService;

    public ReservationDto createReservation(ReservationDto reservationDto) {
        CustomerDto customerDto = reservationDto.getCustomer();
        CustomerDto savedCustomerDto = customerService.createCustomer(customerDto);
        Customer savedCustomer = new Customer();
        savedCustomer.setCustomerId(savedCustomerDto.getId());
        savedCustomer.setFirstName(savedCustomerDto.getFirstName());
        savedCustomer.setLastName(savedCustomerDto.getLastName());
        savedCustomer.setAddress(savedCustomerDto.getAddress());
        savedCustomer.setCity(savedCustomerDto.getCity());
        savedCustomer.setState(savedCustomerDto.getState());
        savedCustomer.setPhone(savedCustomerDto.getPhone());
        savedCustomer.setEmail(savedCustomerDto.getEmail());
//        savedCustomer.setManager(savedCustomerDto.isManager());
        UserDto userDto = savedCustomerDto.getUser();
        UserDto savedUserDto = userService.getUserByUsername(userDto.getUsername());
        User savedUser = new User();
        savedUser.setId(savedUserDto.getId());
        savedUser.setUsername(savedUserDto.getUsername());
        savedUser.setPassword(savedUserDto.getPassword());
        savedUser.setAdmin(savedUserDto.getAdmin());
        savedCustomer.setUser(savedUser);
        Set<RoomDto> roomDtos = reservationDto.getRooms();
        Set<Room> rooms = new HashSet<>();
        for (RoomDto roomDto : roomDtos) {
            RoomDto savedRoomDto = roomService.getRoomById(roomDto.getId());
            Room savedRoom = new Room();
            savedRoom.setRoomId(savedRoomDto.getId());
            savedRoom.setRoomType(RoomType.valueOf(savedRoomDto.getRoomType()));
            savedRoom.setAvailability(savedRoomDto.isAvailability());
            savedRoom.setReady(savedRoomDto.isReady());
            savedRoom.setPrice(savedRoomDto.getPrice());
            savedRoom.setBooked(savedRoomDto.isBooked());
            rooms.add(savedRoom);
        }
        Reservation reservation = new Reservation();
        reservation.setCustomer(savedCustomer);
        reservation.setRooms(rooms);
        reservation.setReservationDate(LocalDate.now());
        reservation.setDateIn(reservationDto.getDateIn());
        reservation.setDateOut(reservationDto.getDateOut());
        reservation.setDateRange(reservationDto.getDateRange());
        UserDto savedUserDto2 = userService.getUserByUsername(reservationDto.getUser().getUsername());
        User savedUser2 = new User();
        savedUser2.setId(savedUserDto2.getId());
        savedUser2.setUsername(savedUserDto2.getUsername());
        savedUser2.setPassword(savedUserDto2.getPassword());
        savedUser2.setAdmin(savedUserDto2.getAdmin());
        reservation.setUser(savedUser2);
        Reservation savedReservation = reservationRepository.save(reservation);
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
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(reservation.getCustomer().getCustomerId());
        customerDto.setFirstName(reservation.getCustomer().getFirstName());
        customerDto.setLastName(reservation.getCustomer().getLastName());
        customerDto.setAddress(reservation.getCustomer().getAddress());
        customerDto.setCity(reservation.getCustomer().getCity());
        customerDto.setState(reservation.getCustomer().getState());
        customerDto.setPhone(reservation.getCustomer().getPhone());
        customerDto.setEmail(reservation.getCustomer().getEmail());
//        customerDto.setManager(reservation.getCustomer().isManager());
        UserDto userDto = new UserDto();
        userDto.setId(reservation.getCustomer().getUser().getId());
        userDto.setUsername(reservation.getCustomer().getUser().getUsername());
        userDto.setPassword(reservation.getCustomer().getUser().getPassword());
        userDto.setAdmin(reservation.getCustomer().getUser().getAdmin());
        customerDto.setUser(userDto);
        reservationDto.setCustomer(customerDto);
        Set<RoomDto> roomDtos = new HashSet<>();
        for (Room room : reservation.getRooms()) {
            RoomDto roomDto = new RoomDto();
            roomDto.setId(room.getRoomId());
            roomDto.setRoomType(room.getRoomType().toString());
            roomDto.setAvailability(room.getAvailability());
            roomDto.setReady(room.isReady());
            roomDto.setPrice(room.getPrice());
            roomDto.setBooked(room.isBooked());
            roomDtos.add(roomDto);
        }
        reservationDto.setRooms(roomDtos);
        reservationDto.setReservationDate(reservation.getReservationDate());
        reservationDto.setDateIn(reservation.getDateIn());
        reservationDto.setDateOut(reservation.getDateOut());
        reservationDto.setDateRange(reservation.getDateRange());
        UserDto userDto2 = new UserDto();
        userDto2.setId(reservation.getUser().getId());
        userDto2.setUsername(reservation.getUser().getUsername());
        userDto2.setPassword(reservation.getUser().getPassword());
        userDto2.setAdmin(reservation.getUser().getAdmin());
        reservationDto.setUser(userDto2);
        return reservationDto;
    }

    public List<ReservationDto> getAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        List<ReservationDto> reservationDtos = new ArrayList<>();
        for (Reservation reservation : reservations) {
            ReservationDto reservationDto = new ReservationDto();
            reservationDto.setId(reservation.getId());
            CustomerDto customerDto = new CustomerDto();
            customerDto.setId(reservation.getCustomer().getCustomerId());
            customerDto.setFirstName(reservation.getCustomer().getFirstName());
            customerDto.setLastName(reservation.getCustomer().getLastName());
            customerDto.setAddress(reservation.getCustomer().getAddress());
            customerDto.setCity(reservation.getCustomer().getCity());
            customerDto.setState(reservation.getCustomer().getState());
            customerDto.setPhone(reservation.getCustomer().getPhone());
            customerDto.setEmail(reservation.getCustomer().getEmail());
//            customerDto.setManager(reservation.getCustomer().isManager());
            UserDto userDto = new UserDto();
            userDto.setId(reservation.getCustomer().getUser().getId());
            userDto.setUsername(reservation.getCustomer().getUser().getUsername());
            userDto.setPassword(reservation.getCustomer().getUser().getPassword());
            userDto.setAdmin(reservation.getCustomer().getUser().getAdmin());
            customerDto.setUser(userDto);
            reservationDto.setCustomer(customerDto);
            Set<RoomDto> roomDtos = new HashSet<>();
            for (Room room : reservation.getRooms()) {
                RoomDto roomDto = new RoomDto();
                roomDto.setId(room.getRoomId());
                roomDto.setRoomType(room.getRoomType().toString());
                roomDto.setAvailability(room.getAvailability());
                roomDto.setReady(room.isReady());
                roomDto.setPrice(room.getPrice());
                roomDto.setBooked(room.isBooked());
                roomDtos.add(roomDto);
            }
            reservationDto.setRooms(roomDtos);
            reservationDto.setReservationDate(reservation.getReservationDate());
            reservationDto.setDateIn(reservation.getDateIn());
            reservationDto.setDateOut(reservation.getDateOut());
            reservationDto.setDateRange(reservation.getDateRange());
            UserDto userDto2 = new UserDto();
            userDto2.setId(reservation.getUser().getId());
            userDto2.setUsername(reservation.getUser().getUsername());
            userDto2.setPassword(reservation.getUser().getPassword());
            userDto2.setAdmin(reservation.getUser().getAdmin());
            reservationDto.setUser(userDto2);
            reservationDtos.add(reservationDto);
        }
        return reservationDtos;
    }

    public ReservationDto updateReservation(ReservationDto reservationDto) {
        Reservation reservation = reservationRepository.findById(reservationDto.getId()).orElse(null);
        if (reservation == null) {
            return null;
        }
        CustomerDto customerDto = reservationDto.getCustomer();
        CustomerDto savedCustomerDto = customerService.updateCustomer(customerDto);
        Customer savedCustomer = new Customer();
        savedCustomer.setCustomerId(savedCustomerDto.getId());
        savedCustomer.setFirstName(savedCustomerDto.getFirstName());
        savedCustomer.setLastName(savedCustomerDto.getLastName());
        savedCustomer.setAddress(savedCustomerDto.getAddress());
        savedCustomer.setCity(savedCustomerDto.getCity());
        savedCustomer.setState(savedCustomerDto.getState());
        savedCustomer.setPhone(savedCustomerDto.getPhone());
        savedCustomer.setEmail(savedCustomerDto.getEmail());
//        savedCustomer.setManager(savedCustomerDto.isManager());
        UserDto userDto = savedCustomerDto.getUser();
        UserDto savedUserDto = userService.getUserByUsername(userDto.getUsername());
        User savedUser = new User();
        savedUser.setId(savedUserDto.getId());
        savedUser.setUsername(savedUserDto.getUsername());
        savedUser.setPassword(savedUserDto.getPassword());
        savedUser.setAdmin(savedUserDto.getAdmin());
        savedCustomer.setUser(savedUser);
        Set<RoomDto> roomDtos = reservationDto.getRooms();
        Set<Room> rooms = new HashSet<>();
        for (RoomDto roomDto : roomDtos) {
            RoomDto savedRoomDto = roomService.getRoomById(roomDto.getId());
            Room savedRoom = new Room();
            savedRoom.setRoomId(savedRoomDto.getId());
            savedRoom.setRoomType(RoomType.valueOf(savedRoomDto.getRoomType()));
            savedRoom.setAvailability(savedRoomDto.isAvailability());
            savedRoom.setReady(savedRoomDto.isReady());
            savedRoom.setPrice(savedRoomDto.getPrice());
            savedRoom.setBooked(savedRoomDto.isBooked());
            rooms.add(savedRoom);
        }
        reservation.setCustomer(savedCustomer);
        reservation.setRooms(rooms);
        reservation.setDateIn(reservationDto.getDateIn());
        reservation.setDateOut(reservationDto.getDateOut());
        reservation.setDateRange(reservationDto.getDateRange());
        UserDto savedUserDto2 = userService.getUserByUsername(reservationDto.getUser().getUsername());
        User savedUser2 = new User();
        savedUser2.setId(savedUserDto2);
        return reservationDto;
    }
}
