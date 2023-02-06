package it.proactivity.demospringboot.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class ProjectDto {

    private Long id;

    private String name;

    private String reportingId;
    private String endDate;


    public ProjectDto(Long id, String name, String endDate) {
        this.id = id;
        this.name = name;
        this.endDate = endDate;
    }

    public ProjectDto(String name, String endDate, String reportingId) {
        this.name = name;
        this.endDate = endDate;
        this.reportingId = reportingId;
    }


    @Override
    public String toString() {
        return """
                ID : %s,
                NAME : %s,
                END_DATE : %s
                                
                """.formatted(this.getId(), this.getName(), this.getEndDate());
    }
}
