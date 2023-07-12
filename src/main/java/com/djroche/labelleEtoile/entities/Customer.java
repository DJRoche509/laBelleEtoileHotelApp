package com.djroche.labelleEtoile.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Customers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    @Column (name = "firstName", nullable = false)
    private String firstName;

    @Column (name = "lastName", nullable = false)
    private String lastName;

    @Column (name = "address")
    private String address;

    @Column (name = "city")
    private String city;

    @Column (name = "state")
    private String state;

    @Column (name = "phone")
    private String phone;

    @Column (name = "email")
    private String email;
}
