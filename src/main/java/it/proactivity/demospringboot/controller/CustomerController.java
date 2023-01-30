package it.proactivity.demospringboot.controller;

import it.proactivity.demospringboot.dto.CustomerDto;
import it.proactivity.demospringboot.model.Customer;
import it.proactivity.demospringboot.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping("/customer/details-list")
    public List<Customer> getAllCustomers() {
        return customerService.customerAllInformationList();
    }

    @GetMapping("/customer/info-id")
    public Customer getCustomerById(@RequestParam Long id) {
        return customerService.customerById(id);
    }

    @GetMapping("/customer/list-of-names")
    public List<Customer> getCustomerWithName8 (@RequestParam String name) {
        return customerService.getAllCustomersWithAName(name);
    }



}
