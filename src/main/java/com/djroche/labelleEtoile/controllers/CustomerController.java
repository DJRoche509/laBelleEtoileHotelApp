package com.djroche.labelleEtoile.controllers;

import com.djroche.labelleEtoile.dtos.CustomerDto;
import com.djroche.labelleEtoile.services.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customers")  //("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/")
    public String createCustomer(@ModelAttribute("customerDto") CustomerDto customerDto) {
        customerService.createCustomer(customerDto);
        return "redirect:/customers";
    }

    @GetMapping("/{id}")
    public String getCustomerById(@PathVariable Long id, Model model) throws ChangeSetPersister.NotFoundException {
        CustomerDto customerDto = customerService.getCustomerById(id);
        if (customerDto == null) {
            throw new ChangeSetPersister.NotFoundException(); //("Customer not found");
        }
        model.addAttribute("customer", customerDto);
        return "customerDetails";
    }

    @GetMapping("/{id}/edit")
    public String showCustomerEdit(@PathVariable Long id, Model model) throws ChangeSetPersister.NotFoundException {
        CustomerDto customerDto = customerService.getCustomerById(id);
        if (customerDto == null) {
            throw new ChangeSetPersister.NotFoundException(); //"Customer not found");
        }
        model.addAttribute("customerDto", customerDto);
        model.addAttribute("edit", true);
        return "customerForm";
    }

    @GetMapping("/")
    public String getAllCustomers(Model model) {
        model.addAttribute("customers", customerService.getAllCustomers());
//        List<CustomerDto> customers = customerService.getAllCustomers();
        return "customerList";
    }

    @GetMapping("/new")
    public String newCustomerForm(Model model) {
        model.addAttribute("customerDto", new CustomerDto());
        return "customerForm";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteCustomer(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        CustomerDto customerDto = customerService.getCustomerById(id);
        if (customerDto == null) {
            throw new ChangeSetPersister.NotFoundException();  // "Customer not found");
        }
        customerService.deleteCustomerById(id);
        return "redirect:/customers";
    }
}
