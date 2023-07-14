package com.djroche.labelleEtoile.entities;

import com.djroche.labelleEtoile.dtos.CustomerDto;
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
    private Long customerId;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @Column (name = "firstName")
    private String firstName;

    @Column (name = "lastName")
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

    /**
     *
     * @return {String} the full name of the customer
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getUserUsername() {
        if (user != null) {
            return user.getUsername();
        } else {
            return "";
        }
    }

    public CustomerDto toDto() {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customerId);
        customerDto.setFirstName(firstName);
        customerDto.setLastName(lastName);
        customerDto.setAddress(address);
        customerDto.setCity(city);
        customerDto.setState(state);
        customerDto.setPhone(phone);
        customerDto.setEmail(email);
        return customerDto;
    }
}