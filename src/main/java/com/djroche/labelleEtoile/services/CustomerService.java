package com.djroche.labelleEtoile.services;

import com.djroche.labelleEtoile.dtos.CustomerDto;
import com.djroche.labelleEtoile.dtos.UserDto;
import com.djroche.labelleEtoile.entities.Customer;
import com.djroche.labelleEtoile.repositories.CustomerRepository;
import com.djroche.labelleEtoile.entities.User;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserService userService;

    public CustomerDto createCustomer(CustomerDto customerDto) {
        UserDto userDto = new UserDto();
        userDto.setUsername(customerDto.getUser().getUsername());
        userDto.setPassword(customerDto.getUser().getPassword());
        userDto.setAdmin(customerDto.getUser().getAdmin());
        UserDto savedUserDto = userService.createUser(userDto);
        User savedUser = new User();
        savedUser.setId(savedUserDto.getId());
        savedUser.setUsername(savedUserDto.getUsername());
        savedUser.setPassword(savedUserDto.getPassword());
        savedUser.setAdmin(savedUserDto.getAdmin());
        Customer customer = new Customer();
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setAddress(customerDto.getAddress());
        customer.setCity(customerDto.getCity());
        customer.setState(customerDto.getState());
        customer.setPhone(customerDto.getPhone());
        customer.setEmail(customerDto.getEmail());
//        customer.setManager(customerDto.isManager());
        customer.setUser(savedUser);
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
        customerDto.setAddress(customer.getAddress());
        customerDto.setCity(customer.getCity());
        customerDto.setState(customer.getState());
        customerDto.setPhone(customer.getPhone());
        customerDto.setEmail(customer.getEmail());
//        customerDto.setManager(customer.isManager());
        UserDto userDto = new UserDto();
        userDto.setId(customer.getUser().getId());
        userDto.setUsername(customer.getUser().getUsername());
        userDto.setPassword(customer.getUser().getPassword());
        userDto.setAdmin(customer.getUser().getAdmin());
        customerDto.setUser(userDto);
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
            customerDto.setAddress(customer.getAddress());
            customerDto.setCity(customer.getCity());
            customerDto.setState(customer.getState());
            customerDto.setPhone(customer.getPhone());
            customerDto.setEmail(customer.getEmail());
//            customerDto.setManager(customer.isManager());
            UserDto userDto = new UserDto();
            userDto.setId(customer.getUser().getId());
            userDto.setUsername(customer.getUser().getUsername());
            userDto.setPassword(customer.getUser().getPassword());
            userDto.setAdmin(customer.getUser().getAdmin());
            customerDto.setUser(userDto);
            customerDtos.add(customerDto);
        }
        return customerDtos;
    }
}
