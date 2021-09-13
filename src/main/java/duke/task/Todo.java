package duke.task;

/**
 * The Todo class is a subclass of Task which
 * implements its functionality.
 *
 * @author richwill28
 */
public class Todo extends Task {
    /**
     * The constructor method. Initialize task description
     * and set the initial status to "not done".
     *
     * @param description Task description.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * The constructor method. Initialize task description
     * and set the initial status according to the given
     * parameter.
     *
     * @param description Task description.
     * @param isDone Initial status.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String serialize() {
        return "T" + " | " + (isDone ? "1" : "0") + " | " + description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
