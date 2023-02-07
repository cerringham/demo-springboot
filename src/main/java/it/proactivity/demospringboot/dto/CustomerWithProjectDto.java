package it.proactivity.demospringboot.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CustomerWithProjectDto {

    private String name;
    private List<ProjectDto> projectDtoList;

    public CustomerWithProjectDto(String name, List<ProjectDto> projectDtoList) {
        this.name = name;
        this.projectDtoList = projectDtoList;
    }
}
