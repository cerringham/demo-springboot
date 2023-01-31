package it.proactivity.demospringboot.utility;

import it.proactivity.demospringboot.model.Customer;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.List;

public class CustomerUtility {
    public List<Customer> getAll() {
        Session session = QueryUtils.createSession();
        String queryString = "SELECT c from Customer c";
        List<Customer> result = session.createQuery(queryString).getResultList();
        QueryUtils.endSession(session);

        return result;
    }

    public Customer getCustomerById(Long id) {
        Session session = QueryUtils.createSession();
        String query = "SELECT c FROM Customer c WHERE c.id = :id";
        Query<Customer> customerQuery = session.createQuery(query)
                .setParameter("id", id);
        Customer customer;
        try {
            customer = customerQuery.getSingleResult();
        }
        catch (NoResultException e) {
            return null;
        }
        QueryUtils.endSession(session);
        return customer;
    }

    public List<Customer> getAllCustomersByName(String name) {
        Session session = QueryUtils.createSession();
        String query = "SELECT c FROM Customer c WHERE c.name LIKE :name";
        Query<Customer> customerQuery = session.createQuery(query)
                        .setParameter("name",  "%" + name + "%");
        List<Customer> result = customerQuery.getResultList();
        QueryUtils.endSession(session);
        return result;
    }
    /*public Customer addNewCustomer(String name, String email, String phoneNumber, String detail) {
       Session session = QueryUtils.createSession();
       String query = "INSERT INTO Customer (name, email, phone_number, detail)" +
               "VALUES (:name, :email, :phoneNumber, :detail)";
       Query<Customer> customerQuery = session.createSQLQuery(query)
               .setParameter("name", name)
               .setParameter("email", email)
               .setParameter("phoneNumber", phoneNumber)
               .setParameter("detail", detail);

       Customer customer = customerQuery.getSingleResult();
       QueryUtils.endSession(session);
       return customer;

    }*/
    public Boolean addNewCustomer(Session session, String name, String email, String phoneNumber, String detail) {
        if (session == null) {
            return false;
        }
        QueryUtils.checkSession(session);

        Customer customer = addCustomer(name, email, phoneNumber, detail);
        session.persist(customer);
        QueryUtils.endSession(session);
        return true;
    }

    public Customer addCustomer(String name, String email, String phoneNumber, String detail) {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setEmail(email);
        customer.setPhoneNumber(phoneNumber);
        customer.setDetail(detail);
        return customer;
    }


}
