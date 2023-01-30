package it.proactivity.demospringboot.utility;

import it.proactivity.demospringboot.model.Company;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.List;

public class CompanyUtility {

    public List<Company> getAll() {
        Session session = QueryUtils.createSession();
        String queryString = "SELECT c from Company c";
        List<Company> result = session.createQuery(queryString).getResultList();

        QueryUtils.endSession(session);

        return result;
    }
}
