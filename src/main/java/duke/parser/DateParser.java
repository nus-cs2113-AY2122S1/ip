package duke.parser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateParser {

    /* Date and time formats that are accepted by the parser */
    private static final String[] dateFormats = {"d{sep}M{sep}yyyy", "MMM{sep}dd{sep}yyyy", "yyyy{sep}M{sep}d"};
    private static final String[] dateSeperators = {"-", "/", " "};

    private static final String[] timeFormats = {"hh:mm:ss a", "H:mm", "h:mm a", "HH:mm:ss", "HHmm"};

    /**
     * checks if the given string is a date
     *
     * @param dateString String to be checked if it is a date
     * @return LocalDate object if dateString is an accepted date, null otherwise
     */
    public static LocalDate parseDate(String dateString) {
        for (String format : dateFormats) {
            for (String seperator : dateSeperators) {
                String dateFormat = prepareFormat(format, seperator);

                try {
                    return LocalDate.parse(dateString, DateTimeFormatter.ofPattern(dateFormat));
                } catch (Exception ignored) {
                }
            }
        }
        return null;
    }

    /**
     * checks if the given string is a time
     *
     * @param timeString String to be checked if it is a time
     * @return LocalTime object if dateString is an accepted time, null otherwise
     */
    public static LocalTime parseTime(String timeString) {
        for (String format : timeFormats) {
            try {
                return LocalTime.parse(timeString, DateTimeFormatter.ofPattern(format));
            } catch (Exception ignored) {
            }
        }
        return null;
    }

    private static String prepareFormat(String dateFormat, String seperator) {
        return dateFormat.replace("{sep}", seperator);
    }

}
