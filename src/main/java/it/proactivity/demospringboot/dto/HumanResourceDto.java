package it.proactivity.demospringboot.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HumanResourceDto {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private String vatCode;
    private Boolean isCeo;
    private Boolean isCda;

    public HumanResourceDto(Long id, String name, String surname, String email, String phoneNumber,
                            String vatCode, Boolean isCeo, Boolean isCda) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.vatCode = vatCode;
        this.isCeo = isCeo;
        this.isCda = isCda;
    }

    public HumanResourceDto(String name, String surname, String email, String phoneNumber,
                            String vatCode, Boolean isCeo, Boolean isCda) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.vatCode = vatCode;
        this.isCeo = isCeo;
        this.isCda = isCda;
    }
}
