package it.proactivity.demospringboot.controller;

import it.proactivity.demospringboot.dto.ProjectDto;
import it.proactivity.demospringboot.dto.SimpleProjectDto;
import it.proactivity.demospringboot.model.Customer;
import it.proactivity.demospringboot.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @GetMapping("/project/basic-info-list")
    public List<SimpleProjectDto> getBasicInformationProject() {
        return projectService.projectBasicInformation();
    }

    @GetMapping("/project/details-list")
    public List<ProjectDto> getAllInformationProject() {
        return projectService.projectAllInformationList();
    }

    @GetMapping("/project/add-new")
    public Boolean newProject(@RequestParam String name, @RequestParam LocalDate endDate, String reportingId,
                              @RequestParam String customerName) {
        return projectService.addNewProject(name, endDate, reportingId, customerName);
    }

}
