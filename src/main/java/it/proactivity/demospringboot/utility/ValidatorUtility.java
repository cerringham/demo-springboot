package it.proactivity.demospringboot.utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ValidatorUtility {

    public static Boolean correctStringName(String s) {
        if (s.contains("!") && s.contains("?") && s.contains("*") && s.contains("/") && s.contains("|")
                && s.contains("@"))
            return false;
        if (s.isEmpty())
            return false;
        if (s == null)
            return false;
        return true;
    }

    public static Boolean correctReportingId(String s) {
        if (s.contains(""))
            return false;
        return true;
    }

    public static Boolean correctEndDate(String s) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate date = LocalDate.parse(s, formatter);
            if (date != null)
                return true;
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

}
