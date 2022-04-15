package task;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event.
 */
public class EventTask extends TimedTask {

    /**
     * Constructs the EventTask object
     * @param task The name/description of the task
     * @param datetime The time limit
     */
    public EventTask(String task, String datetime) {
        super(task, datetime);
    }

    /**
     * Prints the TimedTask as a String
     * @return The task in human-friendly format
     */
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String datetimeString = datetime.format(formatter);
        return super.toString() + String.format(" (at: %s)", datetimeString);
    }

    /**
     * Get the type icon of the task.
     * @return The type icon
     */
    @Override
    public String getTypeIcon() {
        return "E";
    }
}
