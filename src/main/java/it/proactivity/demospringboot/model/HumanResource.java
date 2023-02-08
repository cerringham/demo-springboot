package it.proactivity.demospringboot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "human_resource")
@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
public class HumanResource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "vat_code")
    private String vatCode;

    @Column(name = "flag_ceo")
    private Boolean isCeo;

    @Column(name = "flag_cda")
    private Boolean isCda;

    @Override
    public String toString() {
        return "Name : " + getName() + ", Surname : " + getSurname() + ", Email : " + getEmail() + "\n " +
                "IsCeo : " + getIsCeo() + ", IsCda : " + getIsCda();
    }
}
