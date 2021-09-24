package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    /** Deadline date and time */
    protected LocalDateTime dueDateTime;

    public Deadline(String description, LocalDateTime dueDateTime) {
        super(description);
        this.dueDateTime = dueDateTime;
    }

    @Override
    public LocalDateTime getDT() {
        return dueDateTime;
    }

    @Override
    public String toFileFormat() {
        return "D # " + (isDone ? "1" : "0") + " # " + description + " # " +
                dueDateTime + "\n";
    }

    @Override
    public String toString() {
        return "[D] [" + getStatus() + "] " + getDescription() + " (by: " +
                dueDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")) +
                ")";
    }
}