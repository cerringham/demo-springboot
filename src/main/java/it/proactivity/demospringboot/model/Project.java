package it.proactivity.demospringboot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "project")
@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "reporting_id")
    private String reportingId;

    @ManyToOne
    private Customer customer;

    @Override
    public String toString() {
        return "project name : " + getName() + ", Expire date : " + getEndDate();
    }
}