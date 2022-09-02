package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Utils {

    public static LocalDate converteStringLocalDate(String data) {
       if(data == null || data.isBlank())
           return null;

        return LocalDate.parse(data, DateTimeFormatter.ISO_LOCAL_DATE);
    }
}
