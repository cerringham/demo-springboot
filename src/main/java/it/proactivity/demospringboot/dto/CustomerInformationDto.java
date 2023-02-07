package it.proactivity.demospringboot.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class CustomerInformationDto {

    private String customerName;
    private List<ProjectDto> projects ;

    public CustomerInformationDto(String customerName, List<ProjectDto> projects) {
        this.customerName = customerName;
        this.projects = projects;
    }
}
