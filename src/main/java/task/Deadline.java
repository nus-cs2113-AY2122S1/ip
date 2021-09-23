package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    /** Deadline date and time */
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toFileFormat() {
        return "D # " + (isDone ? "1" : "0") + " # " + description + " # " +
                by + "\n";
    }

    @Override
    public String toString() {
        return "[D] [" + getStatus() + "] " + getDescription() + " (by: " +
                by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")) + ")";
    }
}