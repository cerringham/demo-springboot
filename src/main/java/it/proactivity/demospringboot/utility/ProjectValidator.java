package it.proactivity.demospringboot.utility;


import org.apache.commons.lang3.StringUtils;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ProjectValidator {

    public static Boolean validateName(String name) {
        return StringUtils.isAlpha(name);
    }

    public static Boolean validateEndDate(String date) {
        if (date == null || date.isEmpty()) {
            return false;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            String rightFormat = date.formatted(formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static Boolean validateReportingId(String reportingId) {
        if (reportingId == null || reportingId.isEmpty() || reportingId.contains(" ")) {
            return false;
        }
        return true;
    }

    public static Boolean validateAllBasicParameters(String name, String date, String reportingId) {
        return validateName(name) && validateEndDate(date) && validateReportingId(reportingId);
    }
}
