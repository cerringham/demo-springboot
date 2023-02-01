package it.proactivity.demospringboot.service;

import it.proactivity.demospringboot.dto.ProjectCustomerDto;
import it.proactivity.demospringboot.dto.ProjectDto;
import it.proactivity.demospringboot.model.Project;
import it.proactivity.demospringboot.utility.ProjectUtility;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    ProjectUtility projectUtility = new ProjectUtility();

    public List<ProjectDto> getAllProject() {

        List<Project> projects = projectUtility.getAllProject();

        List<ProjectDto> projectDtos = projects.stream()
                .map(p -> new ProjectDto(p.getId(), p.getName(), p.getEndDate()))
                .toList();

        return projectDtos;
    }

    public List<ProjectCustomerDto> getAllProjectWithCustomerInformation() {
        List<Project> projects = projectUtility.getAllProject();
        List<ProjectCustomerDto> projectCustomerDtos = projects.stream()
                .map(p -> new ProjectCustomerDto(p.getId(), p.getName(), p.getEndDate(), p.getReportingId(),
                        p.getCustomer().getName(), p.getCustomer().getDetail()))
                .toList();

        return projectCustomerDtos;
    }

    public Integer insertProject(String name, String endDate, String reportingId, String customerName) {
        try{
            return projectUtility.insertProject(name, endDate, reportingId, customerName);
        }catch (Exception e) {
            return null;
        }
    }
}