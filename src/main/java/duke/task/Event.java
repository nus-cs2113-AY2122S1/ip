package duke.task;

import java.time.LocalDateTime;

import duke.ui.Ui;

/**
 * Represents a task that starts at a specific time.
 */
public class Event extends Task {
    public static final String TASK_TYPE_ICON = "E";
    /** Date and time of the event */
    private final LocalDateTime at;

    /**
     * Creates a task with the specified description and event date/time.
     *
     * @param description Description of the event.
     * @param at Date and time of the event.
     */
    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns the event date and time.
     *
     * @return Event date and time.
     */
    public LocalDateTime getAt() {
        return at;
    }

    @Override
    public String getTaskTypeIcon() {
        return TASK_TYPE_ICON;
    }

    /**
     * Returns a string representation of the event (consisting of the event date and time, as well as the string
     * representation from the parent class).
     *
     * @return A string representation of the event.
     */
    @Override
    public String toString() {
        return super.toString() + " (at: " + at.format(Ui.DATE_TIME_OUTPUT_FORMATTER) + ")";
    }
}
