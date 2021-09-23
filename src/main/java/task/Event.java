package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    /** Event date and time */
    protected LocalDateTime at;

    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toFileFormat() {
        return "E # " + (isDone ? "1" : "0") + " # " + description + " # " + at + "\n";
    }

    @Override
    public String toString() {
        return "[E] [" + getStatus() + "] " + getDescription() + " (at: " +
                at.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")) + ")";
    }
}