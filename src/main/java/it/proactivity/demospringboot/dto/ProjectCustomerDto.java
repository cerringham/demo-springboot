package it.proactivity.demospringboot.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ProjectCustomerDto extends ProjectDto{

    private String customerName;

    private String customerDetail;

    public ProjectCustomerDto(Long id, String name, String endDate, String customerName, String customerDetail, String detail) {
        super(id, name, endDate);
        this.customerName = customerName;
        this.customerDetail = customerDetail;
    }
}
