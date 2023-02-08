package it.proactivity.demospringboot.service;

import it.proactivity.demospringboot.dto.HumanResourceDto;
import it.proactivity.demospringboot.model.HumanResource;
import it.proactivity.demospringboot.utility.HumanResourceUtility;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class HumanResourceService {
    HumanResourceUtility humanResourceUtility = new HumanResourceUtility();

    public List<HumanResourceDto> getAllHumanResources() {
        List<HumanResource> humanResources = humanResourceUtility.getAllHumanResources();
        if (humanResources.isEmpty() || humanResources == null) {
            return null;
        }
        List<HumanResourceDto> humanResourceDtos = humanResources.stream().map(h ->
                new HumanResourceDto(h.getId(), h.getName(), h.getSurname(), h.getEmail(), h.getPhoneNumber(), h.getVatCode(),
                        h.getIsCeo(), h.getIsCda())).collect(Collectors.toList());
        return humanResourceDtos;
    }

    public HumanResourceDto getHumanResourceFromId(Long id) {
        HumanResource humanResource = humanResourceUtility.getHumanResourceFromId(id);
        if (humanResource == null) {
            return null;
        }
        HumanResourceDto humanResourceDto = new HumanResourceDto(humanResource.getId(), humanResource.getName(),
                humanResource.getSurname(), humanResource.getEmail(), humanResource.getPhoneNumber(),
                humanResource.getVatCode(), humanResource.getIsCeo(), humanResource.getIsCda());
        return humanResourceDto;
    }

    public HumanResourceDto getHumanResourceFromNameAndSurname(String name, String surname) {
        HumanResource humanResource = humanResourceUtility.getHumanResourceFromNameAndSurname(name, surname);
        if (humanResource == null) {
            return null;
        }
        HumanResourceDto humanResourceDto = new HumanResourceDto(humanResource.getId(), humanResource.getName(),
                humanResource.getSurname(), humanResource.getEmail(), humanResource.getPhoneNumber(),
                humanResource.getVatCode(), humanResource.getIsCeo(), humanResource.getIsCda());
        return humanResourceDto;
    }

    public List<HumanResourceDto> getHumanResourcesByCda(Boolean isCda) {
        List<HumanResource> humanResources = humanResourceUtility.getHumanResourcesByCda(isCda);
            if (humanResources == null) {
                return null;
            }
            List<HumanResourceDto> humanResourceDtos = humanResources.stream().map(h ->
                    new HumanResourceDto(h.getId(), h.getName(), h.getSurname(), h.getEmail(), h.getPhoneNumber(),
                            h.getVatCode(), h.getIsCeo(), h.getIsCda())).collect(Collectors.toList());
            return humanResourceDtos;
    }

    public HumanResource insertNewHumanResource(HumanResourceDto humanResourceDto) {
        HumanResource humanResource = humanResourceUtility.insertNewHumanResource();

    }
}
