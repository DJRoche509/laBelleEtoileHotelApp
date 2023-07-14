package com.djroche.labelleEtoile.dtos;

import com.djroche.labelleEtoile.entities.Customer;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String phone;
    private String email;
    private UserDto user;

    public CustomerDto(Customer customer) {
        this.id = customer.getCustomerId();
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();
        this.address = customer.getAddress();
        this.city = customer.getCity();
        this.state = customer.getState();
        this.phone = customer.getPhone();
        this.email = customer.getEmail();
        this.user = new UserDto(customer.getUser());
    }
}
