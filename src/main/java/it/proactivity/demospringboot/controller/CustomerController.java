package it.proactivity.demospringboot.controller;

import it.proactivity.demospringboot.dto.CustomerDto;
import it.proactivity.demospringboot.model.Customer;
import it.proactivity.demospringboot.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/customers")
    public List<CustomerDto> getAllCustomers() {
        List<CustomerDto> customers = customerService.getAllCustomers();

        return customers;
    }

    @GetMapping("/customers/{id}")
    public CustomerDto getCustomer(@PathVariable Long id) {
        CustomerDto customer = customerService.getCustomerFromId(id);
        return customer;
    }

    @GetMapping("/customer-like")
    public List<CustomerDto> getCustomerLike(@RequestParam String name) {
        List<CustomerDto> customers = customerService.findCustomerLike(name);
        return customers;
    }
}
