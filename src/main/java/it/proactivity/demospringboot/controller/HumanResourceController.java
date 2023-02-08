package it.proactivity.demospringboot.controller;

import it.proactivity.demospringboot.dto.HumanResourceDto;
import it.proactivity.demospringboot.service.HumanResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HumanResourceController {

    @Autowired
    HumanResourceService humanResourceService;

    @GetMapping("/all-human-resources")
    public List<HumanResourceDto> getAllHumanResources() {
        return humanResourceService.getAllHumanResources();
    }

    @GetMapping("/human-resource-from-id")
    public HumanResourceDto getHumanResourceFromId(@RequestParam Long id) {
        return humanResourceService.getHumanResourceFromId(id);
    }

    @GetMapping("/human-resource-from-name-surname")
    public HumanResourceDto getHumanResourceFromNameAndSurname(@RequestParam String name, @RequestParam String surname) {
        return humanResourceService.getHumanResourceFromNameAndSurname(name, surname);
    }

    @GetMapping("/human-resources-by-cda")
    public List<HumanResourceDto> getHumanResourcesByCda(@RequestParam Boolean isCda) {
        return humanResourceService.getHumanResourcesByCda(isCda);
    }

    @PostMapping("/insert-human-resource")
    public Boolean insertHumanResource(@RequestBody HumanResourceDto humanResourceDto) {
        return humanResourceService.insertHumanResource(humanResourceDto);
    }

}
