package it.proactivity.demospringboot.controller;

import it.proactivity.demospringboot.dto.CustomerDto;
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


    @RequestMapping(value = "/insert-customer", method = {RequestMethod.GET, RequestMethod.POST})
    public String insertCustomer(@RequestParam String name, @RequestParam String email,
                                 @RequestParam String phoneNumber, @RequestParam String detail) throws Exception {

        if ((customerService.insertCustomer(name, email, phoneNumber, detail)).equals(1)) {
            return "Insert successful";
        } else {
            return "Insert failed";
        }

    }

    @RequestMapping(value = "/update-customer", method = {RequestMethod.GET, RequestMethod.PUT})
    public String updateCustomer(@RequestParam Long id, @RequestParam String name, @RequestParam String email)
            throws IllegalArgumentException {
        if ((customerService.updateCustomer(id, name, email)).equals(0)) {
            return "Update successfull";
        } else {
            return "Update failed";
        }
    }

    @RequestMapping(value = "/delete-customer/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String deleteCustomer(@PathVariable Long id) {
        if ((customerService.deleteCustomer(id)).equals(-1)) {
            return "Delete successfull";
        } else {
            return "Delete failed";
        }
    }
}
