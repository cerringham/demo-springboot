package it.proactivity.demospringboot.service;

import it.proactivity.demospringboot.dto.CustomerDto;
import it.proactivity.demospringboot.model.Customer;
import it.proactivity.demospringboot.utility.CustomerUtility;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class CustomerService {

    CustomerUtility customerUtility = new CustomerUtility();

    public List<CustomerDto> customerAllInformationList() {
        List<Customer> customerList = customerUtility.getAll();
        return customerList.stream().map(f -> new CustomerDto(f.getId(), f.getName(), f.getEmail(), f.getPhoneNumber(),
                f.getDetail())).collect(Collectors.toList());
    }

    public Customer customerById(Long id) {
        Customer customer = customerUtility.getCustomerById(id);
        return customer;
    }

    public List<Customer> getAllCustomersWithAName(String name) {
        List<Customer> customerList = customerUtility.getAllCustomersByName(name);
        return customerList;
    }
}
