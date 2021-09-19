package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Inherits the <code>Task</code> class, adding in a time to represent an event.
 */
public class Event extends Task {

    protected LocalDateTime at;


    /**
     * Constructs an unfinished Event task with the time of the event.
     *
     * @param description Description of the task.
     * @param at Time of the event.
     */
    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    /**
     * Constructs an Event task with a description, time and status of the event.
     *
     * @param description Description of the task.
     * @param at Deadline time of the task.
     * @param isDone Indicates whether the task is done or not.
     */
    public Event(String description, LocalDateTime at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    /**
     * Gets the time of the event.
     *
     * @return Time of the event.
     */
    public LocalDateTime getAt() {
        return at;
    }

    /**
     * Overrides the default toString method to display the task in a more suitable format.
     *
     * @return A string showing status of the task, its description, and the date of the event.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[E]" + super.toString() + " (at: " + at.format(formatter) + ")";
    }
}
