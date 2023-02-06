package it.proactivity.demospringboot.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectCustomerDto extends ProjectDto{

    private String customerName;
    private String customerEmail;
    private String customerPhoneNumber;
    private String customerDetail;

    public ProjectCustomerDto(String name, String endDate, String reportingId, String customerName, String customerEmail,
                              String customerPhoneNumber, String customerDetail) {
        super(name, endDate, reportingId);
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPhoneNumber = customerPhoneNumber;
        this.customerDetail = customerDetail;
    }
}
