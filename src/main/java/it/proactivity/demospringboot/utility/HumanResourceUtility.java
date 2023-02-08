package it.proactivity.demospringboot.utility;

import it.proactivity.demospringboot.dto.HumanResourceDto;
import it.proactivity.demospringboot.model.HumanResource;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.List;

public class HumanResourceUtility {

    // elenco di tutte le HumanResource (servizio GET senza parametri)
    public List<HumanResource> getAllHumanResources() {
        Session session = QueryUtils.createSession();
        String queryString = "SELECT h FROM HumanResource h";

        Query<HumanResource> query = session.createQuery(queryString);
        List<HumanResource> humanResources = query.getResultList();
        if (humanResources == null) {
            QueryUtils.endSession(session);
            return null;
        }
        QueryUtils.endSession(session);
        return humanResources;
    }

    // recupero di una HumanResource per id (servizio GET con parametro id)

    public HumanResource getHumanResourceFromId(Long id) {
        Session session = QueryUtils.createSession();
        String humanQuery = "SELECT h FROM HumanResource h " +
                "WHERE h.id = :id";
        Query<HumanResource> query = session.createQuery(humanQuery)
                .setParameter("id", id);
        try {
            HumanResource humanResource = query.getSingleResult();
            return humanResource;
        } catch (NoResultException e) {
            QueryUtils.endSession(session);
            return null;
        }
    }

    //recupero di una HumanResource per cognome e nome (servizio GET con parametri nome e cognome case insensitive)
    public HumanResource getHumanResourceFromNameAndSurname(String name, String surname) {
        Session session = QueryUtils.createSession();
        String stringQuery = "SELECT h FROM HumanResource h " +
                "WHERE h.name = :name " +
                "AND h.surname = :surname";
        Query<HumanResource> query = session.createQuery(stringQuery)
                .setParameter("name", name)
                .setParameter("surname", surname);
        try {
            HumanResource humanResource = query.getSingleResult();
            return humanResource;
        } catch (NoResultException e) {
            QueryUtils.endSession(session);
            return null;
        }
    }

    //recupero delle HumanResource che fanno parte del CDA (servizio GET con parametro)
    public List<HumanResource> getHumanResourcesByCda(Boolean isCda) {
        Session session = QueryUtils.createSession();
        String stringQuery = "SELECT h FROM HumanResource h " +
                "WHERE h.isCda = :isCda";
        Query<HumanResource> query = session.createQuery(stringQuery)
                .setParameter("isCda", isCda);
        try {
            List<HumanResource> humanResources = query.getResultList();
            return humanResources;
        } catch (NoResultException e) {
            QueryUtils.endSession(session);
            return null;
        }
    }

    //inserimento di una HumanResource (servizio POST con requestBody)
    public HumanResource insertNewHumanResource(HumanResourceDto humanResourceDto) {
        HumanResource humanResource = new HumanResource();
        humanResource.setId(humanResourceDto.getId());
        humanResource.setName(humanResourceDto.getName());
        humanResource.setPhoneNumber(humanResourceDto.getPhoneNumber());


    }


}
