package it.proactivity.demospringboot.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CustomerDto {

    private Long id;

    private String name;

    private String email;

    private String phoneNumber;

    private String detail;

    public CustomerDto(String name, String email) {
        this.name = name;
        this.email = email;
    }



}
