import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class dateTime {

    public String getDate(String date) {
        LocalDate Date = LocalDate.parse(date);
        return "" + Date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + "";
    }

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

    public String toAnalog(int intHour) {
        int analogHour = intHour - 12;
        return "" + analogHour;
    }
}
