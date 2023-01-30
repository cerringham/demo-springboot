package it.proactivity.demospringboot.service;

import it.proactivity.demospringboot.model.Customer;
import it.proactivity.demospringboot.utility.CustomerUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {


    CustomerUtility customerUtility = new CustomerUtility();

    public List<Customer> getAllCustomers() {
        List<Customer> customers = customerUtility.getAllCustomer();
        if (customers.isEmpty() || customers == null) {
            return null;
        }
        return customers;
    }

    public Customer getCustomerFromId(Long id) {
        Customer customer = customerUtility.getCustomerFromId(id);
        if (customer == null) {
            return null;
        }
        return customer;
    }

    public List<Customer> findCustomerLike(String name) {
        List<Customer> customers = customerUtility.findCustomerLike(name);
        if (customers == null || customers.isEmpty()) {
            return null;
        }
        return customers;
    }
}
