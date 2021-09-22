package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public LocalDate getBy() {
        return by;
    }

    public void setBy(LocalDate by) {
        this.by = by;
    }

    public String getTypeIcon() {
        return "D";
    }

    public String toString() {
        return "[D]" + "[" + this.getStatusIcon() + "] " + this.getDescription() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}