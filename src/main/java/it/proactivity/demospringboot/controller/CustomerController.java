package it.proactivity.demospringboot.controller;

import it.proactivity.demospringboot.dto.CustomerDto;
import it.proactivity.demospringboot.model.Customer;
import it.proactivity.demospringboot.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/create-or-update-customer",method = { RequestMethod.GET, RequestMethod.POST })
    public String createOrUpdateCustomer(@RequestParam Long id, @RequestParam String name, @RequestParam String email,
                                       @RequestParam String phoneNumber, @RequestParam String detail) throws IllegalArgumentException {

        customerService.createOrUpdate(id, name, email, phoneNumber, detail);
        return "insert or update successfull";
    }

    @RequestMapping(value = "/delete-customer/{id}", method = { RequestMethod.GET, RequestMethod.DELETE })
    public String deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
       return "Delete successfull";
    }
}
