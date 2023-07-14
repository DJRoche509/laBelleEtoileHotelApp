package com.djroche.labelleEtoile.entities;

import com.djroche.labelleEtoile.dtos.UserDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table (name = "Users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private  String username;

    @Column (name = "password", nullable = false, unique = true)
    private  String password;

    @Column (name = "admin")
    private Boolean admin;

    // Defines a one-to-one relationship between User and Customer entities, where a user can have only one customer
    // associated with it, and a customer can have only one user associated with it.
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private Customer customer;

    // This method returns the full name of the user if exists, else blank/null
    public String getFullName() {
        // Checks if the customer is not null before getting the customer's first and last name
        if (customer != null) {
            return customer.getFirstName() + " " + customer.getLastName();
        } else {
            return "";
        }
    }
}
