package it.proactivity.demospringboot.utility;

import it.proactivity.demospringboot.dto.HumanResourceDto;
import it.proactivity.demospringboot.model.HumanResource;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.List;

import static it.proactivity.demospringboot.utility.CustomerUtility.checkExistingName;

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
                "WHERE UPPER(h.name) = :name " +
                "AND UPPER(h.surname) = :surname";
        Query<HumanResource> query = session.createQuery(stringQuery)
                .setParameter("name", name.toUpperCase())
                .setParameter("surname", surname.toUpperCase());
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

    public Boolean insertHumanResource(HumanResourceDto humanResourceDto) {
        if (humanResourceDto == null) {
            return false;
        }
        Session session = QueryUtils.createSession();
        String nameExists = checkExistingName(session, humanResourceDto.getName());
        if (nameExists != null) {
            QueryUtils.endSession(session);
            return false;
        }
        HumanResource humanResource = insertNewHumanResource(humanResourceDto);
        if (humanResource == null) {
            QueryUtils.endSession(session);
            return false;
        }
        session.persist(humanResource);
        QueryUtils.endSession(session);
        return true;
    }

    //inserimento di una HumanResource (servizio POST con requestBody)
    private static HumanResource insertNewHumanResource(HumanResourceDto humanResourceDto) {
        HumanResource humanResource = new HumanResource();
        humanResource.setId(humanResourceDto.getId());
        humanResource.setName(humanResourceDto.getName());
        humanResource.setSurname(humanResourceDto.getSurname());
        humanResource.setEmail(humanResourceDto.getEmail());
        humanResource.setPhoneNumber(humanResourceDto.getPhoneNumber());
        humanResource.setVatCode(humanResourceDto.getVatCode());
        humanResource.setIsCeo(humanResourceDto.getIsCeo());
        humanResource.setIsCda(humanResourceDto.getIsCda());

        return humanResource;

    }

    public HumanResourceDto getCeo() {

    }


}
