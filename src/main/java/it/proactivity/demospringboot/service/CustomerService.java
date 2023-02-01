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

    public List<CustomerDto> customerAllInformationList() {
        List<Customer> customerList = customerUtility.getAll();
        return customerList.stream().map(m -> new CustomerDto(m.getId(), m.getName(), m.getEmail(), m.getPhoneNumber(),
                m.getDetail())).collect(Collectors.toList());
    }

    public CustomerDto customerById(Long id) {
        Customer customer = customerUtility.getCustomerById(id);
        CustomerDto customerDto = new CustomerDto(customer.getId(), customer.getName(), customer.getEmail(),
                customer.getPhoneNumber(), customer.getDetail());
        return customerDto;
    }

    public List<CustomerDto> getAllCustomersWithAName(String name) {
        List<Customer> customerList = customerUtility.getAllCustomersByName(name);
        return customerList.stream().map(m -> new CustomerDto(m.getId(), m.getName(), m.getEmail(), m.getPhoneNumber(),
                m.getDetail())).collect(Collectors.toList());
    }

    public Boolean addNewCustomer(String name, String email, String phoneNumber, String detail) {
        Session session = QueryUtils.createSession();
        Boolean customerAdded = customerUtility.addNewCustomer(session, name, email, phoneNumber, detail);
        return customerAdded;
    }

    public Boolean deleteACustomer (Long id) {
        Boolean customerDelete = customerUtility.deleteACustomer(id);
        return customerDelete;
    }

    public Boolean updateACustomer (Long id, String nameParameter, String newValue) {
        Boolean updateCustomer = customerUtility.updateACustomer(id,nameParameter, newValue);
        return updateCustomer;
    }


}
