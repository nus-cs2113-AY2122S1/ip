package duke.task;

import static duke.constants.Messages.OPEN_SQUARE_BRACKET;
import static duke.constants.Messages.CLOSE_SQUARE_BRACKET;
import static duke.constants.TaskConstants.PREFIX_EVENT;

/**
 * A representation of an Event task.
 */
public class Event extends Task {

    protected String time;

    /**
     * Class constructor specifying the description and the date time of Event inheriting abstract class Task.
     * Creates an undone Event.
     *
     * @param description Event description.
     * @param time Event date time.
     */
    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    /**
     * Class constructor specifying the description, the status, and the date time of Event
     * inheriting abstract class Task.
     *
     * @param description Event description.
     * @param isDone Event status (done or undone).
     * @param time Event date time.
     */
    public Event(String description, boolean isDone, String time) {
        super(description, isDone);
        this.time = time;
    }

    /**
     * Returns Event date time.
     *
     * @return Event date time.
     */
    public String getTime() {
        return this.time;
    }

    /**
     * Overrides toString method of class Task to get string representation of Event
     * including the Event prefix ("E"), the status icon, the description, and the date time.
     *
     * @return String representation of Event.
     */
    @Override
    public String toString() {
        return OPEN_SQUARE_BRACKET + PREFIX_EVENT + CLOSE_SQUARE_BRACKET + super.toString() + " (at: " + this.time + ")";
    }
}
