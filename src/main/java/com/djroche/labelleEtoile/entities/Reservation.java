package com.djroche.labelleEtoile.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name= "Reservations")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "customer_id", nullable = false)
//    @JsonIgnoreProperties("reservations")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "status_id")
    @JsonIgnoreProperties("reservations")
    private ReservationStatus status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "room_id")
    @JsonIgnoreProperties("reservations")
    private Room room;

    @ManyToMany( mappedBy = "customer" ,fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "reservation_rooms",
            joinColumns = @JoinColumn(name = "reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "room_id"))
    @JsonManagedReference
    private Set<Room> rooms = new HashSet<>();

    @Column(name = "reservation_date")
    private LocalDate reservationDate;

    @Column(name = "date_in")
    private LocalDate dateIn;

    @Column(name = "date_out")
    private LocalDate dateOut;

    @Column(name = "date_range")
    private Integer dateRange;


    /**
     * addRoom(Room room): This method adds a room to the reservation's set of rooms
     * @param room
     */
    public void addRoom(Room room) {
        rooms.add(room);
        room.getReservations().add(this);
    }

    // removeRoom(Room room): This method removes a room from the reservation's set of rooms.
    public void removeRoom(Room room) {
        rooms.remove(room);
        room.getReservations().remove(this);
    }

    // getTotalPrice(): This method calculates the total price of the reservation based on the price of each room and the duration of the stay.
    public int getTotalPrice() {
        int totalPrice = 0;
        for (Room room : rooms) {
            totalPrice += room.getPrice() * dateRange;
        }
        return totalPrice;
    }

    // getNumberOfGuests(): This method calculates the total number of guests in the reservation based on the number of guests in each room.
    public int getNumberOfGuests() {
        int numberOfGuests = 0;
        for (Room room : rooms) {
            numberOfGuests += room.getNumberOfGuests();
        }
        return numberOfGuests;
    }

    // isAvailable(LocalDate dateIn, LocalDate dateOut): This method checks if the reservation is available for the given date range
    public boolean isAvailable(LocalDate dateIn, LocalDate dateOut) {
        for (Room room : rooms) {
            if (!room.isAvailable(dateIn, dateOut)) {
                return false;
            }
        }
        return true;
    }

    // getRoomIds(): This method returns a set of the IDs of all the rooms in the reservation.
    public Set<Long> getRoomIds() {
        Set<Long> roomIds = new HashSet<>();
        for (Room room : rooms) {
            roomIds.add(room.getRoomId());
        }
        return roomIds;
    }
}