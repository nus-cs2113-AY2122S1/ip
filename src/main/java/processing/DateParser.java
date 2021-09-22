package processing;
import exceptions.DateFormatException;
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

    /*-------------- ALL VALID DT FORMATS --------- */
    private static final String DATETIME_FORMAT1 = "MMM d yyyy HH:mm";

    private static final String TIME_FORMAT1 = "HH:mm";
    private static final String TIME_FORMAT2 = "HHmm";

    private static final String DATE_FORMAT1 = "MMM d yyyy";
    private static final String DATE_FORMAT2 = "MMM d yy";
    private static final String DATE_FORMAT3 = "dd/M/yyyy";
    private static final String DATE_FORMAT4 = "dd/M/yy";

    public static final String[] DATETIME_FORMATS = {
        DATETIME_FORMAT1,
        DATE_FORMAT1 + " " + TIME_FORMAT2,
        DATE_FORMAT2 + " " + TIME_FORMAT1,
        DATE_FORMAT2 + " " + TIME_FORMAT2,
        DATE_FORMAT3 + " " + TIME_FORMAT1,
        DATE_FORMAT3 + " " + TIME_FORMAT2,
        DATE_FORMAT4 + " " + TIME_FORMAT1,
        DATE_FORMAT4 + " " + TIME_FORMAT2,
    };

    public static final String[] DATE_FORMATS = {
            DATE_FORMAT1,
            DATE_FORMAT2,
            DATE_FORMAT3,
            DATE_FORMAT4,
    };

    public static final String[] TIME_FORMATS = {
        TIME_FORMAT1,
        TIME_FORMAT2,
    };

    private static final String END_OF_DAY = "23:59:59";

    /**
     * Builds a new Formatter that is Case Insensitive for a specific Date Time Format
     * @param pattern Format to be built into a DateTimeFormatter
     * @return a DateTimeFormatter that can be used to parse and format LocalDateTime objects
     * @see DateTimeFormatter
     */
    @Contract("_ -> new")
    private static @NotNull DateTimeFormatter buildFormat(String pattern) {
        return new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .parseLenient()
                .appendPattern(pattern)
                .toFormatter();
    }

    /**
     * Parses the input date as LocalTime if keyword 'today' was given. Combines Todays date with
     * LocalTime
     * @param date Input String to be parsed into a LocalTime
     * @return LocalDateTime that is composed of Todays Date and the parsed LocalTime
     * @throws DukeException if input time is in an invalid format
     */
    private static @NotNull LocalDateTime parseTodayDateTime(@NotNull String date) throws DukeException {
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
        throw new DateFormatException();
    }

    /**
     * Parses the date input by specific Date Formats
     * @param date Input String to be parsed into a LocalDate
     * @return A LocalDateTime composed of the parsed LocalDate and 2359 Hours LocalTime
     * @throws DukeException if input date has an invalid format
     */
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
        throw new DateFormatException();

    }

    /**
     * Parses a String Input into a LocalDateTime. Iterates through known formats and returns the
     * first valid parsed DateTime.
     * @param date Input to be parsed
     * @return a LocalDateTime if input is in a valid format.
     * @throws DukeException if input has an invalid date time format
     */
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

    /**
     * Parses an string Input into a LocalDateTime. Calls parseDTByDateTimeFormats
     * Checks if first keyword in input is "today", which calls parseTodayDateTime instead
     * @param date Input date to be parsed
     * @return parsed date time if format was valid
     * @throws DukeException if input has an invalid date time format
     */
    public static LocalDateTime parseDate(@NotNull String date) throws DukeException {
        String lDate = date.trim();
        if (lDate.split(" ")[0].equals("today")) {
            String time = lDate.substring("today".length()).trim();
            return parseTodayDateTime(time);
        }
        return parseDTByDateTimeFormats(lDate);
    }

    /**
     * Formats the date
     * @param date DateTime to be formatted
     * @return the date in String format "MMM d yyyy HH:mm"
     * eg. Oct 10 2015 18:00
     */
    public static @NotNull String formatDate(@NotNull LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern(DATETIME_FORMAT1));
    }

    /**
     * Formats a String representation of a date into 'MMM d yyyy HH:mm'
     * e.g. 20/10/15 1800 -> Oct 20 2015 18:00
     * @param date String representation of a date
     * @return formatted date
     * @throws DukeException if the datetime format fails
     */
    public static @NotNull String formatDate(String date) throws DukeException {
        LocalDateTime localDT = parseDate(date);
        return formatDate(localDT);
    }

}
