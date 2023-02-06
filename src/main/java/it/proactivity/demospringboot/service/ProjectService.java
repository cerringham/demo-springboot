package it.proactivity.demospringboot.service;

import it.proactivity.demospringboot.dto.ProjectCustomerDto;
import it.proactivity.demospringboot.dto.ProjectDto;
import it.proactivity.demospringboot.model.Project;
import it.proactivity.demospringboot.utility.CustomerValidator;
import it.proactivity.demospringboot.utility.ParsingUtility;
import it.proactivity.demospringboot.utility.ProjectUtility;
import it.proactivity.demospringboot.utility.ProjectValidator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    ProjectUtility projectUtility = new ProjectUtility();


    public List<ProjectDto> getAllProject() {
        List<Project> projects = projectUtility.getAllProject();


        List<ProjectDto> projectDtos = projects.stream()
                .map(p -> new ProjectDto(p.getId(), p.getName(), ParsingUtility.parsingDateToString(p.getEndDate())))
                .toList();

        return projectDtos;
    }


    public List<ProjectCustomerDto> getAllProjectWithCustomerInformation() {
        List<Project> projects = projectUtility.getAllProjectWithCustomerInformation();
        List<ProjectCustomerDto> projectCustomerDtos = projects.stream()
                .map(p -> new ProjectCustomerDto(p.getId(), p.getName(), ParsingUtility.parsingDateToString(p.getEndDate()),
                        p.getCustomer().getName(), p.getCustomer().getDetail())).toList();

        return projectCustomerDtos;
    }

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

    public void insertBasicProject(ProjectDto projectDto) {

        ProjectValidator.validateAllBasicParameters(projectDto.getName(), projectDto.getEndDate(),
                projectDto.getReportingId());

        projectUtility.insertBasicProject(projectDto);

    }

    public void insertCompliteProject(ProjectCustomerDto projectCustomerDto) {

        ProjectValidator.validateAllBasicParameters(projectCustomerDto.getName(), projectCustomerDto.getEndDate(),
                projectCustomerDto.getReportingId());

        CustomerValidator.validateAllParameters(projectCustomerDto.getCustomerName(),
                projectCustomerDto.getCustomerEmail(), projectCustomerDto.getCustomerPhoneNumber());

        projectUtility.insertCompliteProject(projectCustomerDto);
    }
}
