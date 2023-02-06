package it.proactivity.demospringboot.dto;

import it.proactivity.demospringboot.model.Project;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class CustomerInformationDto {

    private String customerName;
    private List<ProjectDto> projectDtos;

    public CustomerInformationDto(String customerName, List<ProjectDto> projectDtos) {
        this.customerName = customerName;
        this.projectDtos = projectDtos;
    }
}
