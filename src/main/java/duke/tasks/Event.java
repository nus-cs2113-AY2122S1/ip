package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event class to represent a task which has an event.
 * Parent class is Task class.
 *
 * @param "description" the name of the task.
 * @param "at" event date
 * @return modified message when the toString() method is called.
 */
public class Event extends Task {
    protected LocalDate at;

    /**
     * Constructor
     *
     * @param description description of event
     * @param at date of event
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    /**
     * Overrides the toString() method.
     *
     * @return returns a modified message
     */
    @Override
    public String toString() {
        String atFormatter = at.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return "[E]" + super.toString() + " (at: " + atFormatter + ")";
    }

    /**
     * Changes event task in list format
     * to saved file format
     *
     * @return string of event task in saved file format
     */
    @Override
    public String toStringStore() {
        String storeString = "E | ";
        if (isDone) {
            storeString += "1 | ";
        }
        else {
            storeString += "0 | ";
        }
        storeString += description + " | " + at;
        return storeString;
    }

    /**
     * Returns the date of event
     *
     * @return LocalDate type of date of event
     */
    @Override
    public LocalDate getDate() {
        return at;
    }
}
