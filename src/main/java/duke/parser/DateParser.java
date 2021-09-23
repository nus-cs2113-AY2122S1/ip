package duke.parser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateParser {

    private static final String[] dateFormats = {"d{sep}M{sep}yyyy", "yyyy{sep}M{sep}d"};
    private static final String[] dateSeperators = {"-", "/"};

    private static final String[] timeFormats = {"hh:mm:ss a", "H:mm", "h:mm a", "HH:mm:ss", "HHmm"};

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
