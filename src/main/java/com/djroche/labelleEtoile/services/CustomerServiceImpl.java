package com.djroche.labelleEtoile.services;

import com.djroche.labelleEtoile.dtos.CustomerDto;
import com.djroche.labelleEtoile.entities.Customer;
import com.djroche.labelleEtoile.services.UserServiceImpl;
import com.djroche.labelleEtoile.repositories.CustomerRepository;
import com.djroche.labelleEtoile.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    @Transactional
    public List<String> addCustomer(CustomerDto customerDto) {
        List<String> response = new ArrayList<>();
        Customer customer = new Customer();
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setAddress(customerDto.getAddress());
        customer.setCity(customerDto.getCity());
        customer.setState(customerDto.getState());
        customer.setPhone(customerDto.getPhone());
        customer.setEmail(customerDto.getEmail());
        Customer savedCustomer = customerRepository.save(customer);
        customerDto.setId(savedCustomer.getCustomerId());
        customerRepository.saveAndFlush(customer);
        response.add("http://localhost:8080/customerForm.html");
        return response;
    }

    @Override
    public List<String> customerLogin(CustomerDto customerDto) {
        List<String> response = new ArrayList<>();
        Optional<Customer> customerOptional = customerRepository.findByLastName(customerDto.getLastName());
        if (customerOptional.isPresent()){
            if (passwordEncoder.matches(userDto.getPassword(), userOptional.get().getPassword())){
                response.add("http://localhost:8080/home.html");
                response.add(String.valueOf(userOptional.get().getId()));
            } else {
                response.add("Username or password incorrect");
            }
        } else {
            response.add("Username or password incorrect");
        }
        return null;
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
