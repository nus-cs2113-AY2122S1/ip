package processing;
import exceptions.DukeException;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;

public class DateParser {

    private static final String DATETIME_FORMAT1 = "MMM d yyyy HH:mm";

    private static final String TIME_FORMAT1 = "HH:mm";
    private static final String TIME_FORMAT2 = "HHmm";

    private static final String DATE_FORMAT1 = "MMM d yyyy";
    private static final String DATE_FORMAT2 = "MMM d yy";
    private static final String DATE_FORMAT3 = "dd/M/yyyy";
    private static final String DATE_FORMAT4 = "dd/M/yy";
    private static final String DATE_FORMAT5 = "EEE";

    private static final String[] DATETIME_FORMATS = {
        DATETIME_FORMAT1,
        DATE_FORMAT1 + " " + TIME_FORMAT2,
        DATE_FORMAT2 + " " + TIME_FORMAT1,
        DATE_FORMAT2 + " " + TIME_FORMAT2,
        DATE_FORMAT3 + " " + TIME_FORMAT1,
        DATE_FORMAT3 + " " + TIME_FORMAT2,
        DATE_FORMAT4 + " " + TIME_FORMAT1,
        DATE_FORMAT4 + " " + TIME_FORMAT2,
        DATE_FORMAT5 + " " + TIME_FORMAT1,
        DATE_FORMAT5 + " " + TIME_FORMAT2,
    };

    private static final String[] TIME_FORMATS = {
        TIME_FORMAT1,
        TIME_FORMAT2,
    };

    private static final String[] DATE_FORMATS = {
        DATE_FORMAT1,
        DATE_FORMAT2,
        DATE_FORMAT3,
        DATE_FORMAT4,
        DATE_FORMAT5
    };

    private static final String END_OF_DAY = "23:59:59";

    @Contract("_ -> new")
    private static @NotNull DateTimeFormatter buildFormat(String pattern) {
        return new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .parseLenient()
                .appendPattern(pattern)
                .toFormatter();
    }

    private static @NotNull LocalDateTime parseTodayDateTime(String date) throws DukeException {
        if (date.trim().isBlank()) {
            return LocalDateTime.of(LocalDate.now(), LocalTime.parse(END_OF_DAY));
        }
        for (String format : TIME_FORMATS) {
            try {
                DateTimeFormatter formatter = buildFormat(format);
                LocalTime lT = LocalTime.parse(date, formatter);
                return LocalDateTime.of(LocalDate.now(),lT);
            } catch (DateTimeParseException e) {
                continue;
            }
        }
        throw new DukeException("Sorry! Unable to parse date!");
    }

    private static @NotNull LocalDateTime parseDTByDateFormats(String date) throws DukeException {
        for (String format : DATE_FORMATS) {
            try {
                DateTimeFormatter formatter = buildFormat(format);
                LocalDate lD = LocalDate.parse(date, formatter);
                return LocalDateTime.of(lD,LocalTime.parse(END_OF_DAY));
            } catch (DateTimeParseException e) {
                continue;
            }
        }
        throw new DukeException("Sorry! Unable to parse DateTime");

    }

    private static LocalDateTime parseDTByDateTimeFormats(String date) throws DukeException {
        for (String format : DATETIME_FORMATS) {
            try {
                DateTimeFormatter formatter = buildFormat(format);
                return LocalDateTime.parse(date, formatter);
            } catch (DateTimeParseException e) {
                continue;
            }
        }
        return parseDTByDateFormats(date);
    }

    public static LocalDateTime parseDate(@NotNull String date) throws DukeException {
        String lDate = date.trim();
        if (lDate.split(" ")[0].equals("today")) {
            String time = lDate.substring("today".length()).trim();
            System.out.println(time);
            return parseTodayDateTime(time);
        }
        return parseDTByDateTimeFormats(lDate);
    }

    public static @NotNull String formatDate(@NotNull LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern(DATETIME_FORMAT1));
    }

    public static @NotNull String formatDate(String date) throws DukeException {
        LocalDateTime localDT = parseDate(date);
        return formatDate(localDT);
    }

}
