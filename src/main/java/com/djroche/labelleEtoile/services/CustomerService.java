package com.djroche.labelleEtoile.services;

import com.djroche.labelleEtoile.dtos.CustomerDto;
import com.djroche.labelleEtoile.dtos.UserDto;
import com.djroche.labelleEtoile.entities.Customer;
import com.djroche.labelleEtoile.repositories.CustomerRepository;
import com.djroche.labelleEtoile.entities.User;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

public interface CustomerService {
    @Transactional
    List<String> addCustomer(CustomerDto customerDto);
    List<String> customerLogin(CustomerDto customerDto);
}
