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
    public Boolean addNewCustomer(Session session, String name, String email, String phoneNumber, String detail) {
        if (session == null && QueryUtils.checkParameters(name) && QueryUtils.checkParameters(email) &&
                QueryUtils.checkParameters(phoneNumber) && QueryUtils.checkParameters(detail) ) {
            return false;
        }
        QueryUtils.checkSession(session);

        Customer customer = addCustomer(name, email, phoneNumber, detail);
        session.persist(customer);
        QueryUtils.endSession(session);
        return true;
    }

    public Boolean deleteACustomer(Long id) {
        List<Customer> updatedCustomerList = getAll();
        Session session = QueryUtils.createSession();
        if (session == null && QueryUtils.checkId(id)) {
            return false;
        }
        String query = "SELECT c FROM Customer c WHERE c.id = :id";
        Query<Customer> customerQuery = session.createQuery(query).setParameter("id", id);
        List<Customer> customerList = customerQuery.getResultList();
        if (customerList.size() > 1 || customerList == null) {
            QueryUtils.endSession(session);
            return false;
        }
        session.delete(customerList.get(0));
        QueryUtils.endSession(session);

        if (updatedCustomerList.size() == updatedCustomerList.size() -1){
            return true;
        }
    }

    public Boolean updateACustomer(Long id, String name, String email, String phoneNumber, String detail) {
        Session session = QueryUtils.createSession();
        if (session == null && QueryUtils.checkId(id) && QueryUtils.checkParameters(name) &&
                QueryUtils.checkParameters(email) && QueryUtils.checkParameters(phoneNumber)) {
            return false;
        }
        Customer customer = getCustomerById(id);
        setCustomer(customer, name, email, phoneNumber, detail);
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

    public static Customer setCustomer(Customer customer, String name, String email, String phoneNumber, String detail) {
        if (name != null) {
            customer.setName(name);
        }
        if (email != null) {
            customer.setEmail(email);
        }
        if (phoneNumber != null) {
            customer.setPhoneNumber(phoneNumber);
        }
        if (detail != null) {
            customer.setDetail(detail);
        }
        return customer;
    }

}
