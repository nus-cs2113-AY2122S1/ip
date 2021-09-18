package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public abstract class TimedTask extends Task{

    LocalDateTime datetime;

    public TimedTask(String name, String datetime) throws IllegalArgumentException{
        super(name);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        try {
            this.datetime = LocalDateTime.parse(datetime, formatter);
        } catch(DateTimeParseException e) {
            String errorMessage = String.format("%s. Please input date-time in this format (yyyy-MM-dd HH:mm)", datetime);
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public LocalDateTime getTime() {
        return datetime;
    }

    public String toFileString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String deadlineString = datetime.format(formatter);
        return super.toFileString() + String.format(";%s", deadlineString);
    }

    public String toString() {
        return super.toString();
    }
}
