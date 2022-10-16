package online.samjones.coursescheduler.Util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    public static final int FULL_TEXT = 1;
    public static final int NUMERIC = 2;

    private static final DateTimeFormatter fullText = DateTimeFormatter.ofPattern("MMMM d yyyy");
    private static final DateTimeFormatter numeric = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static String formatDate(LocalDate input, int formatter){


        switch (formatter) {
            case FULL_TEXT:
                return input.format(fullText);
            case NUMERIC:
                return input.format(numeric);
            default:
                throw new IllegalArgumentException("Not a valid formatter type");
        }
    }

    public static LocalDate parseString(String input, int formatter){
        switch (formatter) {
            case FULL_TEXT:
                return LocalDate.parse(input, fullText);
            case NUMERIC:
                return LocalDate.parse(input, numeric);
            default:
                throw new IllegalArgumentException("Not a valid formatter type");
        }
    }

}
