package it.proactivity.demospringboot.utility;

import it.proactivity.demospringboot.dto.CustomerDto;
import it.proactivity.demospringboot.dto.CustomerInformationDto;
import it.proactivity.demospringboot.dto.ProjectCustomerDto;
import it.proactivity.demospringboot.dto.ProjectDto;
import it.proactivity.demospringboot.model.Customer;
import it.proactivity.demospringboot.model.Project;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;

public class ProjectUtility {


    public List<Project> getAllProjectWithCustomerInformation() {
        Session session = QueryUtils.createSession();
        String getAllProject = "SELECT p FROM Project p LEFT JOIN FETCH p.customer";

        Query<Project> query = session.createQuery(getAllProject);
        List<Project> projects = query.getResultList();
        QueryUtils.endSession(session);
        return projects;
    }

    public List<Project> getAllProject() {
        Session session = QueryUtils.createSession();
        String getAllProject = "SELECT p FROM Project p";

        Query<Project> query = session.createQuery(getAllProject);
        List<Project> projects = query.getResultList();
        QueryUtils.endSession(session);
        return projects;
    }


    public List<Project> getCustomersInformations() {
        List<Project> projects = getAllProjectWithCustomerInformation();
        return projects;
    }



    public Long insertProject(String name, String endDate, String reportingId, String customerName) throws Exception {
        if (name == null || name.isEmpty() || endDate == null || endDate.isEmpty() || reportingId == null ||
                reportingId.isEmpty() || customerName == null || customerName.isEmpty()) {
            return null;
        }
        Session session = QueryUtils.createSession();
        Long recordsBeforInsert = QueryUtils.countRecord(session, "Project");
        Customer customer = findCustomerFromName(session, customerName);
        if (customer == null) {
            QueryUtils.endSession(session);
            return null;
        }

        LocalDate parsedDate = ParsingUtility.parseStringToDate(endDate);
        if (parsedDate == null) {
            QueryUtils.endSession(session);
        }

        Project project = createProject(name, parsedDate, reportingId, customer);
        session.persist(project);
        Long recordsAfterInsert = QueryUtils.countRecord(session, "Project");
        QueryUtils.endSession(session);

        if (recordsBeforInsert < recordsAfterInsert) {
            return 1l;
        } else {
            throw new Exception();
        }
    }

    public Boolean insertBasicProject(ProjectDto projectDto) {
        if (projectDto == null) {
            return false;
        }
        Session session = QueryUtils.createSession();

        LocalDate parsedDate = ParsingUtility.parseStringToDate(projectDto.getEndDate());
        if (parsedDate == null) {
            QueryUtils.endSession(session);
            return false;
        }
        Project project = createProjectWithoutCustomer(projectDto.getName(), parsedDate, projectDto.getReportingId());

        if (project == null) {
            QueryUtils.endSession(session);
            return false;
        }

        session.persist(project);
        QueryUtils.endSession(session);
        return true;
    }

    public void insertCompliteProject(ProjectCustomerDto projectCustomerDto) {
        if (projectCustomerDto == null) {
            throw new IllegalArgumentException("The ProjectCustomerDto is null");
        }

        LocalDate parsedDate = null;
        Session session = QueryUtils.createSession();
        Customer customer;
        try {
            parsedDate = ParsingUtility.parseStringToDate(projectCustomerDto.getEndDate());

            customer = findCustomerFromAllParameters(session, projectCustomerDto.getCustomerName(),
                    projectCustomerDto.getCustomerEmail(), projectCustomerDto.getCustomerPhoneNumber(),
                    projectCustomerDto.getCustomerDetail());

        } catch (DateTimeParseException e) {
            QueryUtils.endSession(session);
            throw new IllegalArgumentException("Cannot parse the endDate " + projectCustomerDto.getEndDate());
        } catch (NoResultException e) {

            customer = CustomerUtility.createCustomer(projectCustomerDto.getCustomerName(),
                    projectCustomerDto.getCustomerEmail(), projectCustomerDto.getCustomerPhoneNumber(),
                    projectCustomerDto.getCustomerDetail());

            session.persist(customer);
        } catch (NullPointerException e) {
            QueryUtils.endSession(session);
            throw new IllegalArgumentException("The parameters cannot be null");
        }

        Project project = createProject(projectCustomerDto.getName(), parsedDate, projectCustomerDto.getReportingId(),
                customer);

        if (project == null) {
            session.clear();
            QueryUtils.endSession(session);
            throw new IllegalStateException("Cannot create project");
        }
        session.persist(project);
        QueryUtils.endSession(session);
    }

    private Customer findCustomerFromAllParameters(Session session, String name, String email, String phoneNumber,
                                                   String detail) {
        String findCustomer = "SELECT c FROM Customer c " +
                "WHERE LOWER(c.name) = LOWER(:name) AND LOWER(c.email) = LOWER(:email) AND " +
                "c.phoneNumber = :phoneNumber AND LOWER(c.detail) = LOWER(:detail)";

        Query<Customer> query = session.createQuery(findCustomer, Customer.class)
                .setParameter("name", name)
                .setParameter("email", email)
                .setParameter("phoneNumber", phoneNumber)
                .setParameter("detail", detail);


        Customer customer = query.getSingleResult();
        return customer;
    }

    private Customer findCustomerFromName(Session session, String name) {
        if (session == null) {
            return null;
        }

        String findCustomerFromName = "SELECT c FROM Customer c WHERE c.name = :name";
        Query<Customer> query = session.createQuery(findCustomerFromName).setParameter("name", name);

        try {
            Customer customer = query.getSingleResult();
            return customer;
        } catch (NoResultException e) {
            return null;
        }
    }

    private Project createProject(String name, LocalDate endDate, String reportingId, Customer customer) {
        if (name == null || name.isEmpty() || endDate == null || reportingId == null || reportingId.isEmpty() ||
                customer == null) {
            return null;
        }

        Project project = new Project();
        project.setName(name);
        project.setEndDate(endDate);
        project.setReportingId(reportingId);
        project.setCustomer(customer);

        return project;
    }

    private Project createProjectWithoutCustomer(String name, LocalDate endDate, String reportingId) {
        if (name == null || name.isEmpty() || endDate == null || reportingId == null || reportingId.isEmpty()) {
            return null;
        }

        Project project = new Project();
        project.setName(name);
        project.setEndDate(endDate);
        project.setReportingId(reportingId);


        return project;
    }

    private String changePrefixOnPhoneNumber(String phoneNumber) {

        return phoneNumber.replace("+", "00");
    }



}
