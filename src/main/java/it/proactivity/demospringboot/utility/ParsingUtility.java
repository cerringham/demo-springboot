package it.proactivity.demospringboot.utility;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ParsingUtility {

    public static LocalDate parseStringToDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate parsedDate = LocalDate.parse(date, formatter);
        return parsedDate;
    }

    public static String parsingDateToString(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        try {
            String formattedDate = date.format(formatter);
            return formattedDate;
        } catch (DateTimeException e) {
            return null;
        }
    }
}
