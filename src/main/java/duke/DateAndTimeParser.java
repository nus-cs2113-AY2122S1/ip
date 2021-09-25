package duke;
/* Importing local files from other packages */

import exception.DukeException;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeParseException;

public class DateAndTimeParser {
    public static String callProcessDateTime(String dateAndTime) {
        try {
            LocalDateTime localDateTime = LocalDateTime.parse(dateAndTime);
            dateAndTime = ProcessDateTime(localDateTime);
        } catch (DateTimeParseException e) {
            dateAndTime = "";
            System.out.println("☹ OOPS!!! The date and time format is Invalid.\n" + e.getMessage());
        } catch (DateTimeException eg) {
            dateAndTime = "";
            System.out.println("☹ OOPS!!! The date and time format is Invalid.\n" + eg.getMessage());
        } catch (DukeException ex) {
            dateAndTime = "";
            System.out.println(ex.getMessage());
        }
        return dateAndTime;

    }

    public static String ProcessDateTime(LocalDateTime localDateTime) throws DukeException {
        String dateAndTime = "";
        String meridium = "a.m.";

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
            meridium = "p.m.";
        }
        if (hour < 10) {
            hours = "0" + hour;
        }
        if (isBefore) {
            dateAndTime = "";
            throw new DukeException("☹ OOPS!!! You cannot schedule a task for the past.");
        } else {
            dateAndTime = month + " " + day + ", " + year + " " + hours + ":" + minutes + " " + meridium;
        }
        return dateAndTime;
    }
}
