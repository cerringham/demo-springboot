package it.proactivity.demospringboot.controller;

import it.proactivity.demospringboot.dto.CustomerDto;
import it.proactivity.demospringboot.dto.CustomerWithProjectDto;
import it.proactivity.demospringboot.dto.ProjectCustomerDto;
import it.proactivity.demospringboot.dto.ProjectDto;
import it.proactivity.demospringboot.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjectController {

    @Autowired
    ProjectService projectService;


    @GetMapping("/projects")
    public List<ProjectDto> getAllProjects() {
        return projectService.getAllProject();
    }


    @GetMapping("/projects-customer-information")
    public List<ProjectCustomerDto> getAllProjectsWithCustomerInfo() {

        return projectService.getAllProjectWithCustomerInformation();
    }


    @RequestMapping(value = "/insert-project", method = {RequestMethod.GET, RequestMethod.POST})
    public String insertProject(@RequestParam String name, @RequestParam String endDate,
                                @RequestParam String reportingId, @RequestParam String customerName) {
        return projectService.insertProject(name, endDate, reportingId, customerName);
    }

    @PostMapping("/insert-basic-project")
    public void insertBasicProject(@RequestBody ProjectDto projectDto) {
        projectService.insertBasicProject(projectDto);
    }

    @PostMapping("/insert-complite-project")
    public void insertCompliteProject(@RequestBody ProjectCustomerDto projectCustomerDto) {
        projectService.insertCompliteProject(projectCustomerDto);
    }

    @GetMapping("/project-with-customer")
    public List<CustomerWithProjectDto> getCustomerWithProjects() {
        return projectService.getCustomerWithProjectInfo();
    }
}
