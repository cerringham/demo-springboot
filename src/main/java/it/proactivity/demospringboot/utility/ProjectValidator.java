package it.proactivity.demospringboot.utility;

import it.proactivity.demospringboot.dto.ProjectDto;

public class ProjectValidator {

    public static Boolean validateName(String name) {
        if (name == null || name.isEmpty() || name.contains(" ")) {
            return true;
        }
        return false;
    }

    public static Boolean validateEndDate(String date) {
        if (date == null || date.isEmpty()) {
            return false;
        }
        return true;
    }

    public static Boolean validateReportingId(String reportingId) {
        if (reportingId == null || reportingId.isEmpty() || reportingId.contains(" ")) {
            return false;
        }
        return true;
    }
}
