package it.proactivity.demospringboot.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class ProjectCustomerDto extends ProjectDto {

    private String customerName;

    private String customerDetail;
    private String customerEmail;
    private String customerPhoneNumber;

    public ProjectCustomerDto(Long id, String name, String endDate, String customerName, String customerDetail) {
        super(id, name, endDate);
        this.customerName = customerName;
        this.customerDetail = customerDetail;

    }

    public ProjectCustomerDto(String name, String endDate, String reportingId, String customerName, String customerEmail,
                              String customerPhoneNumber, String customerDetail) {
        super(name, endDate, reportingId);
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPhoneNumber = customerPhoneNumber;
        this.customerDetail = customerDetail;
    }
}
