package online.samjones.coursescheduler.Entity;

import androidx.room.TypeConverter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateConverter {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @TypeConverter
    public static LocalDate fromString(String value){
        return value == null ? null : LocalDate.parse(value, DATE_TIME_FORMATTER);
    }

    @TypeConverter
    public static String fromLocalDate(LocalDate value){
        return value == null ? null : value.format(DATE_TIME_FORMATTER);
    }
}
