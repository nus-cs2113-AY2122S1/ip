package duke.manager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateAndTimeParser {

    public static DateTimeFormatter stringFormatter = DateTimeFormatter.ofPattern("EEEE, MMMM dd, uuuu hh:mm a");
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm";

    /**
     * Returns the LocalDateTime object after processing
     * the input string
     *
     * @param message input string containing the date and time
     * @return LocalDateTime object "yyyy-MM-dd HH:mm"
     */
    static LocalDateTime processDateAndTime(String message) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        LocalDateTime info = LocalDateTime.parse(message.strip(), formatter);
        return info;
    }
}
