package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task type as Event task.
 */
public class Event extends Task {

    protected String at;
    protected LocalDate formalFormDate = null;
    protected boolean isValid = false;

    /**
     * Constructor of Event Class.
     *
     * @param description String the content of the task.
     * @param at String the date or time of the event task.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
        formalFormDate = LocalDate.parse(at);
        if (formalFormDate != null) {
            isValid = true;
        }
    }

    /**
     * Returns the date or time of the Event task.
     *
     * @return String the date of time of the Event task.
     */
    public String getAt() {
        return at;
    }

    /**
     * Returns a String contains the status, content, date of time of an Event task.
     *
     * @return String representation of an Event task.
     */
    @Override
    public String toString() {
        if (isValid) {
            return "[E]" + super.toString() + " (at: " + formalFormDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
        }
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
