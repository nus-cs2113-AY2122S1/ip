package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task type as Deadline task
 */
public class Deadline extends Task {

    protected String by;
    protected LocalDate formalFormDate = null;
    protected boolean isValid = false;

    /**
     * Constructor of Deadline Class
     *
     * @param description String the content of the task
     * @param by String the due date or time of the task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        formalFormDate = LocalDate.parse(by);
        if (formalFormDate != null) {
            isValid = true;
        }
    }

    /**
     * Returns the due date or time of the task
     *
     * @return String the due date of time of the task
     */
    public String getBy() {
        return by;
    }

    /**
     * Returns a String contains the status, content, due date of time of a task
     *
     * @return String representation of a task
     */
    @Override
    public String toString() {
        if (isValid) {
            return "[D]" + super.toString() + " (by: " + formalFormDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
        }
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
