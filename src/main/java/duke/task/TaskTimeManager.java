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

    /**
     * The format for date and time to be read from the input string
     * Example of the format: 2021-09-18 16:00
     */
    private static final String readFormat = "yyyy-MM-dd HH:mm";

    /**
     * The format for date and time to be printed
     * Example of the format: 5 Oct 2021 16:00
     */
    public static final String printFormat = "d MMM yyyy HH:mm";

    /**
     * Parses a string(of "yyyy-MM-dd HH:mm" format) representing the Date-Time into
     * a LocalDateTime object.
     *
     * @param input the string representing the Date-Time
     * @return the LocalDateTime object converted from the string
     * @throws DateTimeParseException when the string is not in the correct format or contains
     * an invalid Date-Time
     */
    public LocalDateTime parseDateTime(String input) throws DateTimeParseException {
        //The formatter to parse a string representing DateTime into LocalDateTime type
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(readFormat);
        return LocalDateTime.parse(input, formatter);
    }

    /**
     * Converts the LocalDateTime into a string to be stored into the data storage file
     *
     * @param dateTime the LocalDateTime to be converted into the storage string
     * @return the string to be stored into the data storage file
     */
    public String getWriteToFileFormat(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern(readFormat));
    }

    /**
     * Converts the LocalDateTime into a string to be printed for the user to read the
     * Date-Time.
     *
     * @param dateTime the LocalDateTime to be converted into the display string
     * @return the string to be displayed to the user
     */
    public String getDisplayFormat(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern(printFormat));
    }

    /**
     * Returns the current Date-Time of the OS
     *
     * @return the current Date-Time of the OS
     */
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

    /**
     * Returns the value of the Date-Time after adding a certain number of days to it
     *
     * @param originalTime the Date-Time to have days added to it
     * @param days number of days to be added
     * @return the result of adding a certain number of days to the given Date-Time
     */
    public LocalDateTime addDaysToDateTime(LocalDateTime originalTime, int days) {
        return originalTime.plus(days, ChronoUnit.DAYS);
    }
}
