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

    @GetMapping("/projects-customer-information")
    public List<ProjectCustomerDto> getAllProjectsWithCustomerInfo() {

        return projectService.getAllProjectWithCustomerInformation();
    }

    @RequestMapping(value = "insert-project", method = {RequestMethod.GET, RequestMethod.POST})
    public String insertProject(@RequestParam String name, @RequestParam String endDate,
                                 @RequestParam String reportingId, @RequestParam String customerName) {

        Integer insert = projectService.insertProject(name, endDate, reportingId, customerName);
        if (insert.equals(1)) {
            return "Insert successfull";
        }else {
            return "Insert denied";
        }
    }

}
