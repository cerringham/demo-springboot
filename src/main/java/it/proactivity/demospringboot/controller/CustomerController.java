package it.proactivity.demospringboot.controller;

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
    public List<Customer> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();

        return customers;
    }

    @GetMapping("/customers/{id}")
    public Customer getCustomer(@PathVariable Long id) {
        Customer customer = customerService.getCustomerFromId(id);
        return customer;
    }

    @GetMapping("/customer-like")
    public List<Customer> getCustomerLike(@RequestParam String name) {
        List<Customer> customers = customerService.findCustomerLike(name);
        return customers;
    }
}
