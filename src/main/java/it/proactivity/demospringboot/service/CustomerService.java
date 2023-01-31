package it.proactivity.demospringboot.service;

import it.proactivity.demospringboot.dto.CustomerDto;
import it.proactivity.demospringboot.model.Customer;
import it.proactivity.demospringboot.utility.CustomerUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {


    CustomerUtility customerUtility = new CustomerUtility();

    public List<CustomerDto> getAllCustomers() {
        List<Customer> customers = customerUtility.getAllCustomer();
        if (customers.isEmpty() || customers == null) {
            return null;
        }
        List<CustomerDto> customerDtoList = customers.stream()
                .map(c -> new CustomerDto(c.getName(), c.getEmail())).collect(Collectors.toList());
        return customerDtoList;
    }

    public CustomerDto getCustomerFromId(Long id) {
        Customer customer = customerUtility.getCustomerFromId(id);
        if (customer == null) {
            return null;
        }

        CustomerDto customerDto = new CustomerDto(customer.getName(), customer.getEmail());
        return customerDto;
    }

    public List<CustomerDto> findCustomerLike(String name) {
        List<Customer> customers = customerUtility.findCustomerLike(name);
        if (customers == null || customers.isEmpty()) {
            return null;
        }
        List<CustomerDto> customerDtoList = customers.stream()
                .map(c -> new CustomerDto(c.getName(), c.getEmail()))
                .toList();

        return customerDtoList;
    }
}
