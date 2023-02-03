package it.proactivity.demospringboot.utility;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ValidatorUtility {

    public static Boolean validName(String name) {
        if (name == null || name.isEmpty())
            return false;
        StringUtils.isAlphanumericSpace(name);
        return true;
    }

    public static Boolean validReportingId(String reportingId) {
        if (reportingId == null || reportingId.isEmpty() || reportingId.contains(" "))
            return false;
        StringUtils.isAlphanumeric(reportingId);
        return true;
    }

    public static Boolean validEndDate(String endDate) {
        if (endDate == null || endDate.isEmpty())
            return false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate date = LocalDate.parse(endDate, formatter);
            if (date != null)
                return true;
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

}
