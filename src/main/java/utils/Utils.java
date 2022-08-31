package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Utils {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static LocalDate converteStringLocalDate(String data) {
        return LocalDate.parse(data, FORMATTER);
    }
}
