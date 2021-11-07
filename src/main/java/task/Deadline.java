package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents class for deadline tasks.
 */
public class Deadline extends Task {
    protected LocalDate by;
    public static final String type = "D";

    public Deadline(String description, String by){
        super(description);
        this.by = LocalDate.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = LocalDate.parse(by);
    }

    /**
     * Returns the time by which the deadline task has to be completed.
     *
     * @return Time of deadline task as a LocalDate
     */
    public LocalDate getBy() {
        return by;
    }

    /**
     * Overrides the toString method to print the deadline task.
     *
     * @return [D] + statusIcon + description + (at: d MMM yyyy)
     */
    @Override
    public String toString() {
        return "[" + type + "]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
    }

    /**
     * Returns the type of deadline tasks.
     *
     * @return Task type "D"
     */
    @Override
    public String getTaskType() {
        return type;
    }
}