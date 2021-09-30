package duke.data.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A deadline task in the task list.
 */
public class Deadline extends Task {
    protected String by;
    protected LocalDate byDate;

    /**
     * Constructor that sets the description and deadline.
     *
     * @param description a string that contains the task description
     * @param by a string that contains the deadline for the task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructor that sets the description and deadline in LocalDate class.
     *
     * @param description a string that contains the task description
     * @param byDate a LocalDate that contains the deadline for the task
     */
    public Deadline(String description, LocalDate byDate) {
        super(description);
        this.byDate = byDate;
        this.by = byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Returns a String of value by, representing the task deadline
     *
     * @return the deadline of the task
     */
    public String getBy() {
        return by;
    }

    /**
     * Returns byDate, which represents the task deadline
     *
     * @return the deadline of the task
     */
    public LocalDate getByDate() {
        return byDate;
    }

    /**
     * Returns the String representation of the Deadline task
     *
     * @return a String message that contains the status icon, task description and deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
