package ip.src.main.java;

import java.util.Objects;

/**
 * Represents an Event which is an extension of the Event class.<code>Event</code> corresponds to
 * a Task represented by a description and date e.g., <code>Buy milk at 3pm</code>
 */
public class Event extends Task {
    protected String at;

    /**
     * Adding a Event.
     *
     * @param description Description of the task.
     * @param at Time when the Event occurs.
     * @throws DukeException If description is empty.
     */
    public Event(String description, String at) throws DukeException {
        super(description);
        if (Objects.equals(description, " ") || Objects.equals(description, "") ) {
            throw new DukeException();
        }
        else {
            this.at = at;
        }
    }

    /**
     * Returning the description of an Event.
     * @return description of an Event.
     */
    @Override
    public String description() {
        return description + " (at: " + at + ")";
    }

}