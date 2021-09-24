package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    /** Event date and time */
    protected LocalDateTime eventDateTime;

    public Event(String description, LocalDateTime eventDateTime) {
        super(description);
        this.eventDateTime = eventDateTime;
    }

    @Override
    public String toFileFormat() {
        return "E # " + (isDone ? "1" : "0") + " # " + description + " # " +
                eventDateTime + "\n";
    }

    @Override
    public LocalDateTime getDT() {
        return eventDateTime;
    }

    @Override
    public String toString() {
        return "[E] [" + getStatus() + "] " + getDescription() + " (at: " +
                eventDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"))
                + ")";
    }
}