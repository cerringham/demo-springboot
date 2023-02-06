package it.proactivity.demospringboot.controller;

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

    /*@GetMapping("/projects-customer-information")
    public List<ProjectCustomerDto> getAllProjectsWithCustomerInfo() {

        return projectService.getAllProjectWithCustomerInformation();
    }*/

    @RequestMapping(value = "/insert-project", method = {RequestMethod.GET, RequestMethod.POST})
    public String insertProject(@RequestParam String name, @RequestParam String endDate,
                                @RequestParam String reportingId, @RequestParam String customerName) {
        return projectService.insertProject(name, endDate, reportingId, customerName);
    }

   @PostMapping("/project-new")
    public Boolean insertAProject(@RequestBody ProjectDto projectDto) {
        return projectService.insertNewProject(projectDto);
   }

    @PostMapping("/project-customer-new")
    public Boolean insertAProjectCustomer(@RequestBody ProjectCustomerDto projectDto) {
        return projectService.insertNewProjectWithCustome(projectDto);
    }
}
