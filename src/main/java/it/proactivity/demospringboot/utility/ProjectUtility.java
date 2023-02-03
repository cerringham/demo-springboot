package it.proactivity.demospringboot.utility;

import it.proactivity.demospringboot.dto.ProjectDto;
import it.proactivity.demospringboot.model.Customer;
import it.proactivity.demospringboot.model.Project;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.json.JSONObject;

import javax.persistence.NoResultException;
import java.time.LocalDate;
import java.util.List;

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

        LocalDate parsedDate = ParsingUtility.parsingStringToDate(endDate);
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
        Boolean specialChar = checkSpecialChar(projectDto.getName());

        if (specialChar) {
            QueryUtils.endSession(session);
            return false;
        }
        LocalDate parsedDate = ParsingUtility.parsingStringToDate(projectDto.getEndDate());
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

    private Boolean checkSpecialChar(String name) {
        if (name.contains("!") || name.contains("£") || name.contains("$") || name.contains("%") || name.contains("&")) {
            return true;
        }
        return false;
    }

}
