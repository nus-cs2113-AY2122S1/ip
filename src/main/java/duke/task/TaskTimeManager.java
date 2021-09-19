package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

/**
 * Deals with time related functions such as time parsing and comparing. Time class
 * used is LocalDateTime.
 */
public class TaskTimeManager {

    //The format for date and time to be read from the input string
    //Example of the format: 2021-09-18 16:00
    private static final String readFormat = "yyyy-MM-dd HH:mm";

    //The format for date and time to be printed
    //Example of the format: 5 Oct 2021 16:00
    public static final String printFormat = "d MMM yyyy HH:mm";

    public LocalDateTime parseDateTime(String input) throws DateTimeParseException {
        //The formatter to parse a string representing DateTime into LocalDateTime type
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(readFormat);
        return LocalDateTime.parse(input, formatter);
    }

    public String getWriteToFileFormat(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern(readFormat));
    }

    public String getDisplayFormat(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern(printFormat));
    }

    public LocalDateTime getCurrentTime() {
        return LocalDateTime.now();
    }

    /**
     * Returns "true" if the DateTime given on the left is earlier than the right,
     * otherwise returns "false"
     *
     * @param left DateTime on the left
     * @param right DateTime on the right
     * @return the result of whether left is earlier than right
     */
    public boolean isEarlierThan(LocalDateTime left, LocalDateTime right) {
        return left.isBefore(right);
    }

    public LocalDateTime addDaysToDateTime(LocalDateTime originalTime, int days) {
        return originalTime.plus(days, ChronoUnit.DAYS);
    }
}
