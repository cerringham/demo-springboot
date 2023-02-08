package it.proactivity.demospringboot.controller;

import it.proactivity.demospringboot.dto.HumanResourceDto;
import it.proactivity.demospringboot.model.HumanResource;
import it.proactivity.demospringboot.service.HumanResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HumanResourceController {

    @Autowired
    HumanResourceService humanResourceService;

    @GetMapping("get-all-human-resources")
    public List<HumanResource> getAllHumanResource() {
        return humanResourceService.getAllHumanResource();
    }

    @GetMapping("get-human-resource-from-id")
    public HumanResource getHumanResourceFromId(@PathVariable Long id) {
        return humanResourceService.getHumanResourceFromId(id);
    }

    @GetMapping("get-human-resource-from-name-and-surname")
    public HumanResource getHumanResourceFromNameAndSurname(@RequestParam String name, @RequestParam String surname) {
        return humanResourceService.getHumanResourceFromNameAndSurname(name, surname);
    }

    @GetMapping("get-cda")
    public List<HumanResource> getCda(@RequestParam String isCda) {
        return humanResourceService.getCda(isCda);
    }

    @PostMapping("insert-human-resource")
    public void insertHumanResource(@RequestBody HumanResourceDto humanResourceDto) {
        humanResourceService.insertHumanResource(humanResourceDto);
    }

    @RequestMapping(value = "/delete-human-resource/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public void deleteCustomer(@PathVariable Long id) {
        humanResourceService.deleteHumanResource(id);
    }
}
