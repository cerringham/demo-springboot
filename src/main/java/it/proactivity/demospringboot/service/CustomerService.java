package it.proactivity.demospringboot.service;

import it.proactivity.demospringboot.dto.CustomerDto;
import it.proactivity.demospringboot.model.Customer;
import it.proactivity.demospringboot.utility.CustomerUtility;
import it.proactivity.demospringboot.utility.QueryUtils;
import org.hibernate.Session;
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
        Session session = QueryUtils.createSession();
        Customer customer = customerUtility.getCustomerFromId(session, id);
        if (customer == null) {
            QueryUtils.endSession(session);
            return null;
        }

        QueryUtils.endSession(session);
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

    public Integer insertCustomer(String name, String email, String phoneNumber, String detail) throws Exception {
        return customerUtility.insertCustomer(name, email, phoneNumber, detail);
    }

    public Integer updateCustomer(Long id, String attributeName, String attributeValue) throws IllegalArgumentException {
        Integer update = customerUtility.updateCustomer(id, attributeName, attributeValue);
        return update;
    }

    public Integer deleteCustomer(Long id) {
        if (id == null || id == 0l) {
            return null;
        }
        return customerUtility.deleteCustomer(id);
    }

    public void insertCustomerWithPost(CustomerDto customerDto) {

        customerUtility.insertCustomerWithPost(customerDto);
    }

    public Customer[] getCustomerWithProjects(Long id) {
        return customerUtility.getCustomerWithProjects(id);
    }
}
