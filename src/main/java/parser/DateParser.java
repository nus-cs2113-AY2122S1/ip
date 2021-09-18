package parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The DateParser class implement methods to convert String to Date and Date to String.
 */

public class DateParser {
    /**
     * Converts a date object to string.
     *
     * @param date Date object to be converted to String.
     * @return A string containing date in MMM dd yyyy HHmm format.
     */
    public static String dateTimeToString(Date date) {
        return new SimpleDateFormat("MMM dd yyyy HHmm").format(date);
    }

    /**
     * Converts a date object to string, excluding the time.
     *
     * @param date Date object to be converted to String.
     * @return A string containing date in dd/MM/yyyy format.
     */
    public static String dateToString(Date date) {
        return new SimpleDateFormat("dd/MM/yyyy").format(date);
    }

    /**
     * Converts a date object to string to be written to the file.
     *
     * @param date Date object to be converted to String.
     * @return A string containing date in dd/MM/yyyy HHmm format.
     */
    public static String dateToFile(Date date) {
        return new SimpleDateFormat("dd/MM/yyyy HHmm").format(date);
    }

    /**
     * Formats a string to dd/MM/yyyy format if day/month contains 1 digit.
     *
     * @param date A date string.
     * @return A string containing date in dd/MM/yyyy format. Returns null when it is an invalid date.
     */
    public static String formatDate(String date) {
        try {
            if (date == null) { // Ensure that date is not null
                throw new ParseException("Unknown date", 0);
            }
            Date parsedDate = new SimpleDateFormat("dd/MM/yyyy").parse(date);
            // To ensure date is in the correct format even if day/month has 1 digit
            return new SimpleDateFormat("dd/MM/yyyy").format(parsedDate);
        } catch (ParseException e) {
            // For invalid date formats
        }
        return null;
    }

    /**
     * Converts a string to a Date object.
     *
     * @param date A date string.
     * @return Date object containing date. Returns null when it is an invalid date.
     */
    public static Date stringToDateTime(String date) {
        try {
            if (date == null) { // Ensure that date is not null
                throw new ParseException("Unknown date", 0);
            }
            return new SimpleDateFormat("dd/MM/yyyy HHmm").parse(date);
        } catch (ParseException e) {
            // For invalid date formats
        }
        return null;
    }
}
