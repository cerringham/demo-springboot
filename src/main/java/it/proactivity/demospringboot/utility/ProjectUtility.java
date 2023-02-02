package it.proactivity.demospringboot.utility;

import it.proactivity.demospringboot.model.Customer;
import it.proactivity.demospringboot.model.Project;
import org.hibernate.Session;

import java.time.LocalDate;
import java.util.List;

public class ProjectUtility {

    public static List<Project> getAllProjectsInformation() {
        Session session = QueryUtils.createSession();
        String queryString = "SELECT p from Project p";
        List<Project> result = session.createQuery(queryString).getResultList();
        QueryUtils.endSession(session);
        return result;
    }

    public static Boolean addNewProject(String name, LocalDate endDate, String reportingId, String customerName) {
        Session session = QueryUtils.createSession();
        QueryUtils.checkSession(session);
        if (QueryUtils.checkParameters(name) && endDate == null && QueryUtils.checkParameters(reportingId) &&
                QueryUtils.checkParameters(customerName)) {
            return false;
        }
        Customer customer = CustomerUtility.getCustomerByName(customerName);
        if (customer == null) {
            return false;
        }
        Project project = insertNewProject(name, endDate, reportingId, customer);
        session.persist(project);
        QueryUtils.endSession(session);
        return true;
    }

    public static Project insertNewProject(String name, LocalDate endDate, String reportingId, Customer customer) {
        Project project = new Project();
        project.setName(name);
        project.setEndDate(endDate);
        project.setReportingId(reportingId);
        project.setCustomer(customer);
        return project;
    }


}
