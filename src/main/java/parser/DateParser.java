package parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateParser {
    public static String dateTimeToString(Date date) {
        String dateString = new SimpleDateFormat("MMM dd yyyy HHmm").format(date);
        return dateString;
    }

    public static String dateToString(Date date) {
        String dateString = new SimpleDateFormat("dd/MM/yyyy").format(date);
        return dateString;
    }

    public static String dateToFile(Date date) {
        return new SimpleDateFormat("dd/MM/yyyy HHmm").format(date);
    }


    public static String formatDate(String date) {
        try {
            if (date == null) { // Ensure that date is not null
                throw new ParseException("Unknown date", 0);
            }
            Date parsedDate = new SimpleDateFormat("dd/MM/yyyy").parse(date);
            // To ensure date is in the correct format even if day/month has 1 digit
            String newDate = new SimpleDateFormat("dd/MM/yyyy").format(parsedDate);
            return newDate;
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
