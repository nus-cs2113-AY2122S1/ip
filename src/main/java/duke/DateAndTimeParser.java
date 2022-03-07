package duke;
/* Importing local files from other packages */

import exception.DukeException;
import ui.Ui;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeParseException;

public class DateAndTimeParser {
    /**
     * Storing some error messages as Strings
     */
    public static final String ERROR_PAST_DATE = "☹ OOPS!!! You cannot schedule a task for the past.";

    /**
     * Returns date and time in the string form.
     *
     * @return dateAndTime in the String format.
     */
    public static String callProcessDateTime(String dateAndTime) {
        try {
            LocalDateTime localDateTime = LocalDateTime.parse(dateAndTime);
            dateAndTime = ProcessDateTime(localDateTime);
        } catch (DateTimeParseException e) {
            Ui.printLine();
            dateAndTime = "";
            System.out.println("☹ OOPS!!! The date and time format is Invalid.\n" + e.getMessage());
        } catch (DateTimeException eg) {
            Ui.printLine();
            dateAndTime = "";
            System.out.println("☹ OOPS!!! The date and time format is Invalid.\n" + eg.getMessage());
        } catch (DukeException ex) {
            Ui.printLine();
            dateAndTime = "";
            System.out.println(ex.getMessage());
        }
        return dateAndTime;
    }

    /**
     * Returns date and time after converting it to particular format in the String form.
     * For Example: It decodes the object 2022-08-09 13:00 and converts it to the String "AUGUST 9, 2022 01:00 p.m".
     *
     * @param dateAndTime DateAndTime stores the date and time in a particular form as a string.
     * @param meridium    Meridum stores the anti/post meridium according to the date
     * @param isBefore    IsBefore stores true if the date and time passed as the object is before today's date and timing and false otherwise.
     * @return dateAndTime in the String form
     * @throws DukeException if task is scheduled for the past.
     */
    public static String ProcessDateTime(LocalDateTime localDateTime) throws DukeException {
        String dateAndTime = "";
        String antiMeridium = "a.m.";
        String meridium = antiMeridium;
        String postMeridium = "p.m.";

        Month month = localDateTime.getMonth();

        int day = localDateTime.getDayOfMonth();
        int year = localDateTime.getYear();
        int hour = localDateTime.getHour();
        int minute = localDateTime.getMinute();

        String minutes = Integer.toString(minute);
        String hours = Integer.toString(hour);

        LocalDateTime dateNow = LocalDateTime.now();
        boolean isBefore = localDateTime.isBefore(dateNow);

        if (minute == 0) {
            minutes = "00";
        }
        if (hour > 12) {
            hour -= 12;
            hours = Integer.toString(hour);
            meridium = postMeridium;
        }
        if (hour < 10) {
            hours = "0" + hour;
        }
        if (isBefore) {
            dateAndTime = "";
            throw new DukeException(ERROR_PAST_DATE);
        } else {
            dateAndTime = month + " " + day + ", " + year + " " + hours + ":" + minutes + " " + meridium;
        }
        return dateAndTime;
    }
}
