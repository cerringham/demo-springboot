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
        String getCustomerLike = "SELECT c FROM Customer c WHERE c.name LIKE '%" + name + "%'";
        Query<Customer> query = session.createQuery(getCustomerLike);
        List<Customer> customers = query.getResultList();

        if (customers == null || customers.isEmpty()) {
            QueryUtils.endSession(session);
            return null;
        }
        QueryUtils.endSession(session);
        return customers;
    }

    public Boolean createOrUpdateCustomer(Long id, String name, String email, String phoneNumber, String detail)
            throws IllegalArgumentException {

        if (name == null || name.isEmpty() || email == null || email.isEmpty() || phoneNumber == null || phoneNumber.isEmpty()) {
            return null;
        }
        Session session = QueryUtils.createSession();
        List<Customer> customers = getAllCustomer();
        Boolean nameExists = checkExistingName(customers, name);

        if ((id == null || id == 0l)) {
            //insert customer
            if (!nameExists) {
                Customer customer = createCustomer(name, email, phoneNumber, detail);
                session.persist(customer);
                QueryUtils.endSession(session);
                return true;
            } else {
                QueryUtils.endSession(session);
                throw new IllegalArgumentException("Name already exists");
            }
        } else {
            //update customer
            Customer customerById = getCustomerFromId(id);
            if (customerById == null) {
                QueryUtils.endSession(session);
                return false;
            } else {
                if (nameExists) {
                    QueryUtils.endSession(session);
                    throw new IllegalArgumentException("Name already exists");
                } else {
                    setCustomerParameters(customerById, name, email, phoneNumber, detail);
                    QueryUtils.endSession(session);
                    return true;
                }
            }
        }
    }
    public Boolean deleteCustomer(Long id) {
        if (id == null || id == 0l) {
            return false;
        }
        Session session = QueryUtils.createSession();
        Customer customer = getCustomerFromId(id);

        if (customer == null) {
            QueryUtils.endSession(session);
            return false;
        } else {
            session.delete(customer);
            QueryUtils.endSession(session);
            return true;
        }
    }
    private static Customer createCustomer(String name, String email, String phoneNumber, String detail) {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setEmail(email);
        customer.setPhoneNumber(phoneNumber);
        if (detail != null || !detail.isEmpty())
            customer.setDetail(detail);
        return customer;
    }
    private static void setCustomerParameters(Customer customer, String name, String email, String phoneNumber, String detail) {
        if (name != null || !name.isEmpty()) {
            customer.setName(name);
        }
        if (email != null || !email.isEmpty()) {
            customer.setEmail(email);
        }
        if (phoneNumber != null || !phoneNumber.isEmpty()) {
            customer.setPhoneNumber(phoneNumber);
        }
        if (detail != null || !detail.isEmpty()) {
            customer.setDetail(detail);
        }
    }
    private Boolean checkExistingName(List<Customer> customers, String name) {
        Boolean nameExists = false;
        for (Customer c : customers) {
            if (c.getName().equalsIgnoreCase(name)) {
                nameExists = true;
                break;
            }
        }
        return nameExists;
    }
}
