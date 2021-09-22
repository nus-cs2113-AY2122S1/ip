package unker.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    /**
     * Return a {@link java.util.regex.Matcher} based on the data provided.
     * 
     * Returns null if the whole pattern is not matched.
     *  
     * @param commandPattern The regular expression pattern to match
     * @param data The data to handle.
     * @return A {@link java.util.regex.Matcher} with the results from the pattern.
     */
    public static Matcher parseUserInput(String commandPattern, String data) {
        if (data == null) {
            return null;
        }
        Pattern pattern = Pattern.compile(commandPattern);
        Matcher matcher = pattern.matcher(data);

        // Only return the Matcher if the input is valid
        if (matcher.matches()) {
            return matcher;
        } else {
            return null;
        }
    }

    /**
     * Converts a {@link java.time.LocalDate} to ISO standard.
     * 
     * @param date The date to be converted.
     * @return The date string in ISO format.
     */
    public static String toISODateString(LocalDate date) {
        return date.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    /**
     * Converts a {@link java.time.LocalDate} to Month Day, Year format.
     *
     * @param date The date to be converted.
     * @return The date string in Month Day, Year format.
     */
    public static String toFriendlyDateString(LocalDate date) {
        return date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
    }

    /**
     * Converts a {@link java.time.LocalTime} to ISO standard.
     *
     * @param time The time to be converted.
     * @return The time string in ISO format.
     */
    public static String toISOTimeString(LocalTime time) {
        return time.format(DateTimeFormatter.ISO_LOCAL_TIME);
    }

    /**
     * Converts a {@link java.time.LocalDateTime} to ISO standard.
     *
     * @param datetime The datetime to be converted.
     * @return The datetime string in ISO format.
     */
    public static String toISODateTimeString(LocalDateTime datetime) {
        return datetime.format(DateTimeFormatter.ISO_LOCAL_DATE) + " " + datetime.format(DateTimeFormatter.ISO_LOCAL_TIME);
    }

    /**
     * Converts a {@link java.time.LocalDateTime} to Month Day, Year format.
     *
     * @param datetime The datetime to be converted.
     * @return The datetime string in Month Day, Year format.
     */
    public static String toFriendlyDateTimeString(LocalDateTime datetime) {
        return datetime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
    }
    
}
