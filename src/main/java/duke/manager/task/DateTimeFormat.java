package duke.manager.task;

import java.time.format.DateTimeFormatter;

public class DateTimeFormat {

    public static final String STRING_TO_DATE_TIME_PATTERN = "dd/MM/yyyy HH:mm";
    public static final String DATE_TIME_TO_STRING_PATTERN = "EEE, d MMM yyyy, HH:mm";
    public static final DateTimeFormatter STRING_TO_DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern(STRING_TO_DATE_TIME_PATTERN);
    public static final DateTimeFormatter DATE_TIME_TO_STRING_FORMATTER =
            DateTimeFormatter.ofPattern(DATE_TIME_TO_STRING_PATTERN);
}
