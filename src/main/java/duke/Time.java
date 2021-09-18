package duke;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Time {

    public static String convertDate(String time) {
        DateFormat originalFormat = new SimpleDateFormat("dd MM yyyy hh:mm", Locale.ENGLISH);
        DateFormat targetFormat = new SimpleDateFormat("dd MMM yyyy hh:mm a");
        try {
            Date date = originalFormat.parse(time);
            return targetFormat.format(date);  // 20120821
        } catch (ParseException e) {
            return "Invalid deadline entered! Please enter the deadline in the following format: [dd MM yyyy hh:mm]. You have entered: \"" +
                    time + "\"";
        }
    }

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
