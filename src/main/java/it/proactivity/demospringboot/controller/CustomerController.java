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
    public List<CustomerDto> getAllCustomers() {
        return customerService.customerAllInformationList();
    }

    @GetMapping("/customer/info-id")
    public CustomerDto getCustomerById(@RequestParam Long id) {
        return customerService.customerById(id);
    }

    @GetMapping("/customer/list-of-names")
    public List<CustomerDto> getCustomerWithName8 (@RequestParam String name) {
        return customerService.getAllCustomersWithAName(name);
    }

    @GetMapping("/customer/new-customer")
    public Boolean newCustomer(@RequestParam String name, @RequestParam String email, @RequestParam String phoneNumber,
                               @RequestParam String detail) {
        return customerService.addNewCustomer(name, email, phoneNumber, detail);
    }

    @GetMapping("/customer/delete")
    public Boolean deleteCustomer(@RequestParam Long id) {
        return customerService.deleteACustomer(id);
    }

    @GetMapping("/customer/update")
    public Boolean updateCustomer(@RequestParam Long id, @RequestParam String name, @RequestParam String email,
                                  @RequestParam String phoneNumber, @RequestParam String detail ) {
        return customerService.updateACustomer(id, name, email, phoneNumber, detail);
    }





}
