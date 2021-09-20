package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TimeHandler {
    public static LocalDate getDate(String date) {
        String[] ymdString = date.split("/");
        Integer[] ymd = {0, 0, 0};
        for (int i = 0; i < 3; i += 1) {
            ymd[i] = Integer.parseInt(ymdString[i].trim());
        }
        return LocalDate.of(ymd[0], ymd[1], ymd[2]);
    }

    public static String formatDate(LocalDate ld) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        return ld.format(dtf);
    }
}
