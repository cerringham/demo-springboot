package it.proactivity.demospringboot.service;

import it.proactivity.demospringboot.dto.HumanResourceDto;
import it.proactivity.demospringboot.model.HumanResource;
import it.proactivity.demospringboot.utility.HumanResourceUtility;
import it.proactivity.demospringboot.utility.HumanResourceValidator;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.List;

@Service
public class HumanResourceService {

    HumanResourceUtility utility = new HumanResourceUtility();
    HumanResourceValidator humanResourceValidator = new HumanResourceValidator();

    public Boolean insertHumanResource(HumanResourceDto humanResourceDto) {

        humanResourceValidator.validateAllHumanResourceDtoParameters(humanResourceDto);
        utility.insertHumanResource(humanResourceDto);
        return true;
    }

    public Boolean deleteHumanResource(Long id) {

        humanResourceValidator.validateDeleteCdaOrCeo(id);
        utility.deleteHumanResource(id);
        return true;
    }

    public List<HumanResource> getAllHumanResource() {
        return utility.getAllHumanResource();
    }

    public HumanResource getHumanResourceFromId(Long id) {
        HumanResource humanResource = utility.getHumanResourceFromId(id);
        if (humanResource == null) {
            throw new NoResultException("HumanResource not found");
        }
        return humanResource;
    }

    public HumanResource getHumanResourceFromNameAndSurname(String name, String surname) {
        humanResourceValidator.validateName(name);
        humanResourceValidator.validateSurname(surname);

        HumanResource humanResource = utility.getHumanResourceFromNameAndSurname(name, surname);
        if (humanResource == null) {
            throw new IllegalStateException("HumanResource not found");
        }
        return humanResource;
    }

    public List<HumanResource> getCda(String isCda) {
        List<HumanResource> humanResourceList = utility.getCda(isCda);
        if (humanResourceList == null || humanResourceList.isEmpty()) {
            throw new IllegalArgumentException("No Cda found");
        }
        return humanResourceList;
    }
}
