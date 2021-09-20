package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class to represent a task which has a deadline.
 * Parent class is Task class.
 *
 * @param "description" the name of the task.
 * @param "by" deadline of task
 * @return modified message when the toString() method is called.
 */
public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Overrides the toString() method.
     *
     * @return returns a modified message
     */
    @Override
    public String toString() {
        String byFormatter = by.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return "[D]" + super.toString() + " (by: " + byFormatter + ")";
    }

    /**
     * Changes deadline task in list format
     * to saved file format
     *
     * @return string of deadline task in saved file format
     */
    @Override
    public String toStringStore() {
        String storeString = "D | ";
        if (isDone) {
            storeString += "1 | ";
        }
        else {
            storeString += "0 | ";
        }
        storeString += description + " | " + by;
        return storeString;
    }

    @Override
    public LocalDate getDate() {
        return by;
    }
}
