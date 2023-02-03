package it.proactivity.demospringboot.dto;


import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;


@Getter
@Setter
public class ProjectDto {

    private Long id;
    private String name;
    private String endDate;
    private String reportingId;

    public ProjectDto(Long id, String name, String endDate) {
        this.id = id;
        this.name = name;
        this.endDate = endDate;
    }

    public ProjectDto(String name, LocalDate endDate, String reportingId) {
        this.name = name;
        this.endDate = String.valueOf(endDate);
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
