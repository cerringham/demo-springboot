package it.proactivity.demospringboot.dto;

import it.proactivity.demospringboot.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter

@NoArgsConstructor

public class ProjectDto extends SimpleProjectDto{

    private String reportingId;
    private Customer customer;

    public ProjectDto (Long id, String name, LocalDate endDate, String reportingId, Customer customer) {
        super(id, name, endDate);
        this.reportingId = reportingId;
        this.customer = customer;
    }

}
