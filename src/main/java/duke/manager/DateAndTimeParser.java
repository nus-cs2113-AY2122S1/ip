package duke.manager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateAndTimeParser {

    public static final String DATE_FORMAT_OUTPUT = "EEEE, MMMM dd, uuuu hh:mm a";
    private static final String DATE_FORMAT_INPUT = "yyyy-MM-dd HH:mm";
    public static DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT_INPUT);
    public static DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT_OUTPUT);

    /**
     * Returns the LocalDateTime object after processing
     * the input string according to rules provided in
     * inputFormatter
     *
     * @param message input string containing the date and time
     * @return LocalDateTime object "yyyy-MM-dd HH:mm"
     * @throws DateTimeParseException If date format is incorrect
     */
    static LocalDateTime processDateAndTime(String message) throws DateTimeParseException {
        LocalDateTime taskDateTime = LocalDateTime.parse(message.strip(), inputFormatter);
        return taskDateTime;
    }
}
