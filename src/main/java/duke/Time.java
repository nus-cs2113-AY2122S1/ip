package duke;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/** Converter for Duke that deals with converting the date and time entered by user to a specified, more readable format */
public class Time {

    /**
     * Converts the date from user input from "dd MM yyyy hh:mm" format to "dd MMM yyyy hh:mm a"
     *
     * @param time date/time entered by the user in the format "dd MM yyyy hh:mm" to be converted
     * @return date/time in specified format of "dd MMM yyyy hh:mm a"
     */
    public static String convertDate(String time) {
        DateFormat originalFormat = new SimpleDateFormat("dd MM yyyy hh:mm", Locale.ENGLISH);
        DateFormat targetFormat = new SimpleDateFormat("dd MMM yyyy hh:mm a");
        try {
            Date date = originalFormat.parse(time);
            return targetFormat.format(date);
        } catch (ParseException e) {
            return "Invalid deadline entered! Please enter the deadline in the following format: [dd MM yyyy hh:mm]. You have entered: \"" +
                    time + "\"";
        }
    }

    /**
     * Checks if user has entered a valid format of date/time
     *
     * @param time date/time entered by the user
     * @return true if date/time is valid and entered in the correct format
     */
    public static Boolean isDateValid(String time) {
        DateFormat originalFormat = new SimpleDateFormat("dd MM yyyy hh:mm", Locale.ENGLISH);
        try {
            Date date = originalFormat.parse(time);
            return true;
        } catch (ParseException e) {
            System.out.println("Unable to add task due to invalid deadline entered. Please enter in the format: [dd MM yyyy hh:mm]!");
            return false;
        }

    }
}
