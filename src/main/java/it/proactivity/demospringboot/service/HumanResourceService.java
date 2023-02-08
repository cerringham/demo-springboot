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

        humanResourceValidator.validateAllHumanResourceDtoParametersForInsert(humanResourceDto);
        utility.insertHumanResource(humanResourceDto);
        return true;
    }

    public Boolean deleteHumanResource(Long id) {

        humanResourceValidator.validateCdaOrCeoForDelete(id);
        utility.deleteHumanResource(id);
        return true;
    }

    public List<HumanResourceDto> getAllHumanResource() {
        List<HumanResource> humanResourceList = utility.getAllHumanResource();

        List<HumanResourceDto> humanResourceDtoList = humanResourceList.stream()
                .map(h -> new HumanResourceDto(h.getName(), h.getSurname(), h.getEmail(), h.getPhoneNumber(),
                        h.getVatCode(), h.getIsCeo(), h.getIsCda()))
                .toList();

        return humanResourceDtoList;
    }

    public HumanResourceDto getHumanResourceFromId(Long id) {
        HumanResource humanResource = utility.getHumanResourceFromId(id);
        if (humanResource == null) {
            throw new NoResultException("HumanResource not found");
        }

        HumanResourceDto humanResourceDto = new HumanResourceDto(humanResource.getName(), humanResource.getSurname(),
                humanResource.getEmail(), humanResource.getPhoneNumber(), humanResource.getVatCode(),
                humanResource.getIsCeo(), humanResource.getIsCda());

        return humanResourceDto;
    }

    public HumanResourceDto getHumanResourceFromNameAndSurname(String name, String surname) {
        humanResourceValidator.validateName(name);
        humanResourceValidator.validateSurname(surname);

        HumanResource humanResource = utility.getHumanResourceFromNameAndSurname(name, surname);
        if (humanResource == null) {
            throw new IllegalStateException("HumanResource not found");
        }

        HumanResourceDto humanResourceDto = new HumanResourceDto(humanResource.getName(), humanResource.getSurname(),
                humanResource.getEmail(), humanResource.getPhoneNumber(), humanResource.getVatCode(),
                humanResource.getIsCeo(), humanResource.getIsCda());

        return humanResourceDto;
    }

    public List<HumanResourceDto> getCda() {
        List<HumanResource> humanResourceList = utility.getCda();
        if (humanResourceList == null || humanResourceList.isEmpty()) {
            throw new IllegalArgumentException("No Cda found");
        }

        List<HumanResourceDto> humanResourceDtoList = humanResourceList.stream()
                .map(h -> new HumanResourceDto(h.getName(), h.getSurname(), h.getEmail(), h.getPhoneNumber(),
                        h.getVatCode(), h.getIsCeo(), h.getIsCda()))
                .toList();

        return humanResourceDtoList;
    }
}
