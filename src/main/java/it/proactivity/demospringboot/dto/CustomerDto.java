package it.proactivity.demospringboot.dto;


import it.proactivity.demospringboot.model.Project;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor

public class CustomerDto {

    private Long id;

    private String name;

    private String email;

    private String phoneNumber;

    private String detail;

    private List<Project> projectList;

    public CustomerDto(String name, String email, String phoneNumber, String detail) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.detail = detail;
    }
    public CustomerDto(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public CustomerDto(String name, String email, String phoneNumber, String detail, List<Project> projectList) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.detail = detail;
        this.projectList = projectList;
    }

}
