package parser;

import ui.Ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The DateParser class implement methods to convert String to Date and Date to String.
 */

public class DateParser {
    public static String dateToString(Date date) {
        String dateString = new SimpleDateFormat("MMM dd yyyy HHmm").format(date);
        return dateString;
    }

    public static String dateToFile(Date date) {
        return new SimpleDateFormat("dd/MM/yyyy HHmm").format(date);
    }

    public static String dateWithoutTime(Date date) {
        return new SimpleDateFormat("dd/MM/yyyy").format(date);
    }

    public static Date stringToDate(String date) {
        try {
            if (date == null) { // Ensure that date is not null
                throw new ParseException("Unknown date", 0);
            }
            return new SimpleDateFormat("dd/MM/yyyy").parse(date);
        } catch (ParseException e) {
            // For invalid date formats
        }
        return null;
    }

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
