package it.proactivity.demospringboot.utility;

import it.proactivity.demospringboot.model.Customer;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class CustomerUtility {

    public List<Customer> getAllCustomer() {
        Session session = QueryUtils.createSession();
        String getAllCustomer = "SELECT c FROM Customer c";

        Query<Customer> query = session.createQuery(getAllCustomer);

        List<Customer> customerList = query.getResultList();

        if (customerList == null || customerList.isEmpty()) {
            QueryUtils.endSession(session);
            return null;
        }
        QueryUtils.endSession(session);
        return customerList;
    }

    public Customer getCustomerFromId(Long id) {
        Session session = QueryUtils.createSession();
        String getCustomerFromId = "SELECT c FROM Customer c " +
                "WHERE c.id = :id";
        Query<Customer> query = session.createQuery(getCustomerFromId).setParameter("id", id);

        Customer customer = query.getSingleResult();
        if (customer == null) {
            QueryUtils.endSession(session);
            return null;
        }
        QueryUtils.endSession(session);
        return customer;
    }

    public List<Customer> findCustomerLike(String name) {
        Session session = QueryUtils.createSession();
        String getCustomerLike ="SELECT c FROM Customer c WHERE c.name LIKE '%" + name + "%'";
        Query<Customer> query = session.createQuery(getCustomerLike);
        List<Customer> customers = query.getResultList();

        if (customers == null || customers.isEmpty()) {
            QueryUtils.endSession(session);
            return null;
        }
        QueryUtils.endSession(session);
        return customers;
    }

    public Boolean insertCustomer(Customer customer) {
        Session session = QueryUtils.createSession();
        String insertIntoCustomer = "INSERT INTO Customer c(c.name, c.email, c.phoneNumber, c.detail) " +
                "VALUES(:name, :email, :phoneNumber, :detail)";

        Query<Customer> query = session.createQuery(insertIntoCustomer)
                .setParameter("name", customer.getName())
                .setParameter("email", customer.getEmail())
                .setParameter("phoneNumber", customer.getPhoneNumber())
                .setParameter("detail", customer.getDetail());

        int res = query.executeUpdate();
        if (res != 0) {
            return true;
        }else {
            return false;
        }
    }
}
