package duke.manager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateAndTimeParser {

    private static final String DATE_FORMAT_INPUT = "yyyy-MM-dd HH:mm";
    public static final String DATE_FORMAT_OUTPUT = "EEEE, MMMM dd, uuuu hh:mm a";
    public static DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT_INPUT);
    public static DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT_OUTPUT);

    /**
     * Returns the LocalDateTime object after processing
     * the input string
     *
     * @param message input string containing the date and time
     * @return LocalDateTime object "yyyy-MM-dd HH:mm"
     */
    static LocalDateTime processDateAndTime(String message) throws DateTimeParseException {
        LocalDateTime info = LocalDateTime.parse(message.strip(), inputFormatter);
        return info;
    }
}
