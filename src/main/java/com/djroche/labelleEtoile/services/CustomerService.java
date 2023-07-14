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

@Service
@Transactional
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public CustomerDto createCustomer(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setEmail(customerDto.getEmail());
        Customer savedCustomer = customerRepository.save(customer);
        customerDto.setId(savedCustomer.getCustomerId());
        return customerDto;
    }

    public CustomerDto getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer == null) {
            return null;
        }
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customer.getCustomerId());
        customerDto.setFirstName(customer.getFirstName());
        customerDto.setLastName(customer.getLastName());
        customerDto.setEmail(customer.getEmail());
        return customerDto;
    }

    public List<CustomerDto> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerDto> customerDtos = new ArrayList<>();
        for (Customer customer : customers) {
            CustomerDto customerDto = new CustomerDto();
            customerDto.setId(customer.getCustomerId());
            customerDto.setFirstName(customer.getFirstName());
            customerDto.setLastName(customer.getLastName());
            customerDto.setEmail(customer.getEmail());
            customerDtos.add(customerDto);
        }
        return customerDtos;
    }

    public void deleteCustomerById(Long id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer == null) {
            return;
        }
        customerRepository.deleteById(id);
    }
}
