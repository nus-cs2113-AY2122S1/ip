package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class EventTask extends Task{

    private LocalDateTime datetime;

    public EventTask(String task, String datetime) throws IllegalArgumentException{
        super(task);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        try {
            this.datetime = LocalDateTime.parse(datetime, formatter);
        } catch(DateTimeParseException e) {
            String errorMessage = String.format("%s. Please input date-time in this format (yyyy-MM-dd HH:mm)", datetime);
            throw new IllegalArgumentException(errorMessage);
        }

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


    public String toFileString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String datetimeString = datetime.format(formatter);
        return super.toFileString() + String.format(";%s", datetimeString);
    }
}
