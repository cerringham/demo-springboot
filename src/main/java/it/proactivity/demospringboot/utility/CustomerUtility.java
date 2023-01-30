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

}
