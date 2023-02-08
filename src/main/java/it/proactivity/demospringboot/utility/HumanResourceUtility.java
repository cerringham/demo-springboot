package it.proactivity.demospringboot.utility;

import it.proactivity.demospringboot.dto.HumanResourceDto;
import it.proactivity.demospringboot.model.HumanResource;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.List;

public class HumanResourceUtility {

    public List<HumanResource> getAllHumanResource() {
        Session session = QueryUtils.createSession();
        String getAllHumanResource =
                "SELECT h " +
                        "FROM HumanResource h";
        Query<HumanResource> query = session.createQuery(getAllHumanResource);
        List<HumanResource> humanResourceList = query.getResultList();
        QueryUtils.endSession(session);
        return humanResourceList;
    }

    public Boolean insertHumanResource(HumanResourceDto humanResourceDto) {
        if (humanResourceDto == null) {
            throw new IllegalArgumentException("humanResourceDto can't be null");
        }
        Session session = QueryUtils.createSession();
        HumanResource humanResource = createHumanResource(humanResourceDto);
        session.persist(humanResource);
        QueryUtils.endSession(session);
        return true;
    }

    public HumanResource getHumanResourceFromId(Long id) {
        Session session = QueryUtils.createSession();
        String getHumanResourceFromId = "SELECT h " +
                "FROM HumanResource h " +
                "WHERE h.id = :id";
        Query<HumanResource> query = session.createQuery(getHumanResourceFromId).setParameter("id", id);
        try {
            HumanResource humanResource = query.getSingleResult();
            return humanResource;
        } catch (NoResultException e) {
            return null;
        }
    }

    public Boolean deleteHumanResource(Long id) {

        if (id == null || id.equals(0l)) {
            throw new IllegalArgumentException("The id can't be null or is value equal to 0");
        }
        Session session = QueryUtils.createSession();
        HumanResource humanResource = getHumanResourceFromId(id);
        if (humanResource == null) {
            throw new IllegalArgumentException("HumanResource not found");
        }
        session.delete(humanResource);
        QueryUtils.endSession(session);
        return true;
    }

    public List<HumanResource> getCda(String isCda) {
        if (isCda == null || isCda.isEmpty()) {
            throw new IllegalArgumentException("The value of isCda can't be null or empty");
        }
        Session session = QueryUtils.createSession();
        String getCda = "SELECT h " +
                "FROM HumanResource h " +
                "WHERE h.isCda = :isCda";
        Boolean b = Boolean.getBoolean(isCda);
        Query<HumanResource> query = session.createQuery(getCda).setParameter("isCda", b);
        try {
            List<HumanResource> humanResource = query.getResultList();
            return humanResource;
        } catch (NoResultException e) {
            return null;
        }
    }

    public HumanResource getHumanResourceFromNameAndSurname(String name, String surname) {
        if (name == null || name.isEmpty() || surname == null || surname.isEmpty()) {
            throw new IllegalArgumentException("The value of name and surname can't be null or empty");
        }
        Session session = QueryUtils.createSession();
        String getHumanResourceFromNameAndSurname = "SELECT h " +
                "FROM HumanResource h " +
                "WHERE h.name = :name AND h.surname = :surname";
        Query<HumanResource> query = session.createQuery(getHumanResourceFromNameAndSurname);
        query.setParameter("name", name);
        query.setParameter("surname", surname);
        try {
            HumanResource humanResource = query.getSingleResult();
            return humanResource;
        } catch (NoResultException e) {
            return null;
        }
    }

    private HumanResource createHumanResource(HumanResourceDto humanResourceDto) {

        if (StringUtils.startsWith(humanResourceDto.getPhoneNumber(), "+")) {
            String newPhoneNumber = humanResourceDto.getPhoneNumber().replace("+", "00");
            HumanResource humanResource = new HumanResource();
            humanResource.setName(humanResourceDto.getName());
            humanResource.setSurname(humanResourceDto.getSurname());
            humanResource.setEmail(humanResourceDto.getEmail());
            humanResource.setPhoneNumber(newPhoneNumber);
            humanResource.setVatCode(humanResourceDto.getVatCode());
            humanResource.setIsCeo(humanResourceDto.getIsCeo());
            humanResource.setIsCda(humanResourceDto.getIsCda());
            return humanResource;
        } else {
            HumanResource humanResource = new HumanResource();
            humanResource.setName(humanResourceDto.getName());
            humanResource.setSurname(humanResourceDto.getSurname());
            humanResource.setPhoneNumber(humanResourceDto.getPhoneNumber());
            humanResource.setEmail(humanResourceDto.getEmail());
            humanResource.setVatCode(humanResourceDto.getVatCode());
            humanResource.setIsCeo(humanResourceDto.getIsCeo());
            humanResource.setIsCda(humanResourceDto.getIsCda());
            return humanResource;
        }
    }


}
