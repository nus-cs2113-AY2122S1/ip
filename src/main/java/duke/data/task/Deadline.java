package duke.data.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class is used for tasks that need to be done before a specific date/time.
 * E.g: submit iP by 23/12/2001 2359
 */
public class Deadline extends Task {
    protected LocalDateTime dateAndTime;

    public Deadline(String description, LocalDateTime dateAndTime) {
        super(description);
        this.dateAndTime = dateAndTime;
    }

    public Deadline(String description, LocalDateTime dateAndTime, boolean isDone) {
        super(description, isDone);
        this.dateAndTime = dateAndTime;
    }

    public String getFormattedDateAndTime() {
        return this.dateAndTime.format(DateTimeFormatter.ofPattern("MMM d yyyy h.ma"));
    }

    /**
     * Returns Deadline task formatted in the form "[D][ ] description (by: deadline)"
     *
     * @return Formatted Deadline task string
     */
    @Override
    public String toString() {
        return DEADLINE_LOGO + super.toString() + " (by: " + getFormattedDateAndTime() + ")";
    }

    /**
     * Returns Deadline task formatted for data file in the form "D | 1/0 | description | deadline"
     *
     * @return Formatted Deadline task string for data file
     */
    @Override
    public String toTextFileString() {
        return DEADLINE_ACRONYM + " | " + super.toTextFileString() + " | " + dateAndTime;
    }
}
