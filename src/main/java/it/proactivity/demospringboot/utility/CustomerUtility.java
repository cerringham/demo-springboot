package it.proactivity.demospringboot.utility;

import it.proactivity.demospringboot.dto.CustomerDto;
import it.proactivity.demospringboot.model.Customer;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
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

    public Customer getCustomerFromId(Session session, Long id) {

        String getCustomerFromId = "SELECT c FROM Customer c " +
                "WHERE c.id = :id";
        Query<Customer> query = session.createQuery(getCustomerFromId).setParameter("id", id);

        try {
            Customer customer = query.getSingleResult();

            return customer;
        } catch (NoResultException e) {

            return null;
        }
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

    public Integer insertCustomer(String name, String email, String phoneNumber, String detail)
            throws Exception {

        if (name == null || name.isEmpty() || email == null || email.isEmpty() || phoneNumber == null || phoneNumber.isEmpty()) {
            return null;
        }
        Session session = QueryUtils.createSession();
        String existsName = checkExistingName(session, name);
        Long recordsBeforeInsert = QueryUtils.countRecord(session, "Customer");


        if (existsName == null) {
            Customer customer = createCustomer(name, email, phoneNumber, detail);
            session.persist(customer);
            Long recordsAfterInsert = QueryUtils.countRecord(session, "Customer");
            QueryUtils.endSession(session);

            if (recordsBeforeInsert < recordsAfterInsert) {
                return 1;
            } else {
                throw new Exception();
            }

        } else {
            QueryUtils.endSession(session);
            throw new IllegalArgumentException("Name already exists");
        }
    }

    public Boolean insertCustomerWithPost(CustomerDto customerDto) {
        if (customerDto == null) {
            return false;
        }
        Session session = QueryUtils.createSession();
        String nameExists = checkExistingName(session, customerDto.getName());

        if (nameExists != null) {
            QueryUtils.endSession(session);
            return false;
        }
        Customer customer = createCustomerFromDto(customerDto);
        if (customer == null) {
            QueryUtils.endSession(session);
            return false;
        }

        session.persist(customer);
        QueryUtils.endSession(session);
        return true;
    }

    public Integer updateCustomer(Long id, String attributeName, String attributeValue) throws IllegalArgumentException {

        if (id == null || id == 0l || attributeName == null || attributeName.isEmpty() || attributeValue == null ||
                attributeValue.isEmpty()) {
            return null;
        }
        Session session = QueryUtils.createSession();
        Long recordsBeforeUpdate = QueryUtils.countRecord(session, "Customer");
        Long recordsAfterUpdate = 0l;
        String checkCustomerName = checkExistingName(session, attributeName);

        if (checkCustomerName == null) {
            String existingAttribute = checkExistingAttribute(session, id, attributeName);
            if (existingAttribute == null) {
                updateCustomer(session, id, attributeName, attributeValue);
                recordsAfterUpdate = QueryUtils.countRecord(session, "Customer");
                QueryUtils.endSession(session);
            }
        }
        if (recordsBeforeUpdate == recordsAfterUpdate) {
            return 0;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public Integer deleteCustomer(Long id) {
        if (id == null || id == 0l) {
            return null;
        }
        Session session = QueryUtils.createSession();
        Customer customer = getCustomerFromId(session, id);
        Long recordsBeforDelete = QueryUtils.countRecord(session, "Customer");

        if (customer == null) {
            QueryUtils.endSession(session);
            return null;
        } else {
            session.delete(customer);
            QueryUtils.endSession(session);
            Long recordsAfterDelete = QueryUtils.countRecord(session, "Customer");
            if (recordsAfterDelete < recordsBeforDelete) {
                return -1;
            } else {
                throw new IllegalArgumentException();
            }
        }
    }

    public static Customer createCustomer(String name, String email, String phoneNumber, String detail) {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setEmail(email);
        customer.setPhoneNumber(phoneNumber);
        if (detail != null || !detail.isEmpty())
            customer.setDetail(detail);
        return customer;
    }

    private Customer createCustomerFromDto(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setPhoneNumber(customerDto.getPhoneNumber());
        customer.setDetail(customerDto.getDetail());

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

    private String checkExistingName(Session session, String name) {
        if (session == null) {
            return null;
        }

        if (name == null || name.isEmpty()) {
            return null;
        }

        String customerExists = "SELECT c.name FROM Customer c WHERE UPPER(c.name) = UPPER(:name)";
        Query<String> query = session.createQuery(customerExists).setParameter("name", name);

        try {
            String nameAttribute = query.getSingleResult();

            return nameAttribute;
        } catch (NoResultException e) {
            return null;
        }
    }

    private String checkExistingAttribute(Session session, Long id, String attributeName) {
        String getAttributeValue = "SELECT c." + attributeName + " FROM Customer c WHERE c.id = :id";
        Query<String> query = session.createQuery(getAttributeValue).setParameter("id", id);
        try {
            String attribute = query.getSingleResult();
            return attribute;
        } catch (NoResultException e) {
            return null;
        }
    }

    private Boolean updateCustomer(Session session, Long id, String attributeName, String attributeValue) {
        String updateCustomer = "UPDATE Customer c " +
                "SET c." + attributeName + " = :attributeValue " +
                "WHERE c.id = :id";
        Query query = session.createQuery(updateCustomer).setParameter("id", id)
                .setParameter("attributeValue", attributeValue);

        int res = query.executeUpdate();

        if (res != 0) {
            return true;
        }
        return false;
    }


}
