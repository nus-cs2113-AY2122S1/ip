package task;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class EventTask extends TimedTask {

    public EventTask(String task, String datetime) {
        super(task, datetime);
    }

    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String datetimeString = datetime.format(formatter);
        return super.toString() + String.format(" (at: %s)", datetimeString);
    }

    @Override
    public String getTypeIcon() {
        return "E";
    }
}
