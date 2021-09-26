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

    /**
     * Returns the symbol representing Deadline tasks.
     *
     * @return Returns the character "D"
     */
    public String getTypeIcon() {
        return "D";
    }

    /**
     * Returns a string representation of the Deadline task.
     *
     * @return Returns the task as a String
     */
    public String toString() {
        return "[D]" + "[" + this.getStatusIcon() + "] " + this.getDescription() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}