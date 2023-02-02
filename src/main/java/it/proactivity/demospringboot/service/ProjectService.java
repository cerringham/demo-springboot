package it.proactivity.demospringboot.service;

import it.proactivity.demospringboot.dto.ProjectDto;
import it.proactivity.demospringboot.dto.SimpleProjectDto;
import it.proactivity.demospringboot.model.Project;
import it.proactivity.demospringboot.utility.ProjectUtility;
import it.proactivity.demospringboot.utility.QueryUtils;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class ProjectService {
    public List<ProjectDto> projectAllInformationList() {
        List<Project> projectList = ProjectUtility.getAllProjectsInformation();
        return projectList.stream().map(m -> new ProjectDto(m.getId(),m.getName(), m.getEndDate(), m.getReportingId(),
                m.getCustomer())).collect(Collectors.toList());
    }

    public List<SimpleProjectDto> projectBasicInformation() {
        List<Project> projectList = ProjectUtility.getAllProjectsInformation();
        return projectList.stream().map(m -> new SimpleProjectDto(m.getId(),m.getName(), m.getEndDate()))
                .collect(Collectors.toList());
    }

    public Boolean addNewProject(String name, LocalDate endDate, String reportingId, String customerName) {
        Boolean customerAdded = ProjectUtility.addNewProject(name, endDate, reportingId, customerName);
        return customerAdded;
    }
}
