package duke.task;

import static duke.constants.Messages.OPEN_SQUARE_BRACKET;
import static duke.constants.Messages.CLOSE_SQUARE_BRACKET;
import static duke.constants.TaskConstants.PREFIX_TODO;

/**
 * A representation of a ToDo task.
 */
public class ToDo extends Task {

    /**
     * Class constructor specifying the description of ToDo inheriting abstract class Task.
     * Creates an undone ToDo.
     *
     * @param description ToDo description.
     */
    public ToDo (String description) {
        super(description);
    }

    /**
     * Class constructor specifying the description and the status of ToDo
     * inheriting abstract class Task.
     *
     * @param description ToDo description.
     * @param isDone ToDo status (done or undone).
     */
    public ToDo (String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Overrides toString method class Task to get string representation of ToDo
     * including the ToDo prefix ("T"), the status icon, and the description.
     *
     * @return String representation of ToDo.
     */
    @Override
    public String toString() {
        return OPEN_SQUARE_BRACKET + PREFIX_TODO + CLOSE_SQUARE_BRACKET + super.toString();
    }
}
