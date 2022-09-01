package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Utils {

    public static LocalDate converteStringLocalDate(String data) {
        return LocalDate.parse(data, DateTimeFormatter.ISO_LOCAL_DATE);
    }
}
