package it.proactivity.demospringboot.utility;


import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ProjectValidator {

    public static Boolean validateProjectName(String name) {
        if (StringUtils.isEmpty(name)) {
            return false;
        }
        return StringUtils.isAlphanumericSpace(name);
    }

    public static Boolean validateEndDate(String date) {
        if (StringUtils.isEmpty(date)) {
            return false;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate rightFormat = LocalDate.parse(date, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static Boolean validateReportingId(String reportingId) {
        if (StringUtils.isEmpty(reportingId)) {
            return false;
        }
        return StringUtils.isAlphanumeric(reportingId);
    }

    public static void validateAllBasicParameters(String name, String date, String reportingId) {
        if (!validateProjectName(name)) {
            throw new IllegalArgumentException("Name incorrect : " + name);
        }
        if (!validateEndDate(date)) {
            throw new IllegalArgumentException("Date incorrect : " + date);
        }
        if (!validateReportingId(reportingId)) {
            throw new IllegalArgumentException("ReportingId incorrect : " + reportingId);
        }

    }
}
