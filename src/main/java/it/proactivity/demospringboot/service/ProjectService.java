package it.proactivity.demospringboot.service;

import it.proactivity.demospringboot.dto.ProjectCustomerDto;
import it.proactivity.demospringboot.dto.ProjectDto;
import it.proactivity.demospringboot.model.Project;
import it.proactivity.demospringboot.utility.ProjectUtility;
import it.proactivity.demospringboot.utility.ValidatorUtility;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    ProjectUtility projectUtility = new ProjectUtility();

    public List<ProjectDto> getAllProject() {
        List<Project> projects = projectUtility.getAllProject();

        List<ProjectDto> projectDtos = projects.stream()
                .map(p -> new ProjectDto(p.getId(), p.getName(), String.valueOf(p.getEndDate())))
                .toList();

        return projectDtos;
    }

    /*public List<ProjectCustomerDto> getAllProjectWithCustomerInformation() {
        List<Project> projects = projectUtility.getAllProjectWithCustomerInformation();
        List<ProjectCustomerDto> projectCustomerDtos = projects.stream()
                .map(p -> new ProjectCustomerDto(p.getId(), p.getName(), p.getEndDate(), p.getReportingId(),
                        p.getCustomer().getName(), p.getCustomer().getDetail()))
                .toList();

        return projectCustomerDtos;
    }*/

    public String insertProject(String name, String endDate, String reportingId, String customerName) {
        try {
            Long insert = projectUtility.insertProject(name, endDate, reportingId, customerName);

            if (insert == null) {
                return "Insert denied";
            } else {
                return "Insert successfull";
            }
        } catch (Exception e) {
            return null;
        }
    }

    public Boolean insertNewProject(ProjectDto projectDto) {
        if (checkIfParametersAreValid(projectDto) == false) {
            return false;
        }
        return projectUtility.addNewProject(projectDto);
    }

    public Boolean insertNewProjectWithCustome(ProjectCustomerDto projectDto) {
        if (checkIfParametersAreValid(projectDto) == false) {
            return false;
        }
        return projectUtility.addNewProjectWithCustomer(projectDto);
    }


    public static Boolean checkIfParametersAreValid(ProjectDto projectDto) {
        if (ValidatorUtility.validName(projectDto.getName()) == false ||
                ValidatorUtility.validEndDate(projectDto.getEndDate()) == false ||
                ValidatorUtility.validReportingId(projectDto.getReportingId()) == false) {
            return false;
        }
        return true;
    }
}
