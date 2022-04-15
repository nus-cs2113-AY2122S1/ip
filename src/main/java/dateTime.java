import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * The dateTime program formats date and time to desired format.
 */
public class dateTime {

    /**
     * Converts date from yyyy-mm-dd format to MMM d yyyy format.
     * @param date given in yyyy-mm-dd format
     * @return String Date
     */
    public String getDate(String date) {
        LocalDate Date = LocalDate.parse(date);
        return "" + Date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Converts time from 24-hour format and writes in 12-hour format. E.g. from 1830 to 6.30pm.
     * @param time given in 24-hour format.
     * @return String Time
     */
    public String getTime(String time) {
        String Time;
        String amPm;
        String hour;
        int intHour;
        String min;

        hour = time.substring(1, 3);
        min = time.substring(3, 5);

        intHour = Integer.parseInt(hour);
        if (intHour <= 11) {
            amPm = "am";
        }
        else {
            amPm = "pm";
            hour = toAnalog(intHour);
        }

        if (min.equals("00")) {
            min = "";
        }

        else {
            min = "." + min;
        }
        Time = " " + hour + min + amPm;
        return Time;
    }

    /**
     * Converts integer hour of time from 24-hour to 12-hour format and returns it as string
     * @param intHour hour of time in 24-hour format
     * @return String analogHour
     */
    public String toAnalog(int intHour) {
        int analogHour = intHour - 12;
        return "" + analogHour;
    }
}
