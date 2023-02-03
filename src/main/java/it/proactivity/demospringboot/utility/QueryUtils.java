package it.proactivity.demospringboot.utility;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import javax.persistence.NoResultException;


@Component
public class QueryUtils {

    public static Session createSession() {
        SessionFactory sessionFactory = new Configuration().configure()
                .buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        return session;
    }

    public static void endSession(Session session) {
        session.getTransaction().commit();
        session.close();
    }

    public static void checkSession(Session session) {
        if (!session.isOpen()) {
            session = createSession();
        }
        session.getTransaction();
    }

    public static Long countRecord(Session session, String object) {

        String count = "SELECT COUNT(o.id) FROM "+ object + " o";
        Query<Long> query = session.createQuery(count);
        try{
            Long result = query.getSingleResult();
            return result;
        }catch (NoResultException e) {
            return null;
        }

    }

    public static Boolean correctString(String s) {
        if (s.contains("!") && s.contains("?") && s.contains("*") && s.contains("/") && s.contains("|"))
            return false;
        if (s.isEmpty())
            return false;
        return true;
    }


}
