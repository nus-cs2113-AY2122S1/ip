package duke.task;

import static duke.constants.Messages.OPEN_SQUARE_BRACKET;
import static duke.constants.Messages.CLOSE_SQUARE_BRACKET;
import static duke.constants.TaskConstants.PREFIX_DEADLINE;

/**
 * A representation of a Deadline task.
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Class constructor specifying the description and the date time of Deadline inheriting abstract class Task.
     * Creates an undone Deadline.
     *
     * @param description Deadline description.
     * @param by Deadline date time.
     */
    public Deadline (String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Class constructor specifying the description, the status, and the date time of Deadline
     * inheriting abstract class Task.
     *
     * @param description Deadline description.
     * @param isDone Deadline status (done or undone).
     * @param by Deadline date time.
     */
    public Deadline (String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Returns the date time of Deadline.
     *
     * @return Deadline date time.
     */
    public String getBy() {
        return this.by;
    }

    /**
     * Overrides toString method class Task to get string representation of Deadline
     * including the Deadline prefix ("D"), the status icon, the description, and the date time.
     *
     * @return String representation of Deadline.
     */
    @Override
    public String toString() {
        return OPEN_SQUARE_BRACKET + PREFIX_DEADLINE + CLOSE_SQUARE_BRACKET + super.toString() + " (by: " + by + ")";
    }
}
