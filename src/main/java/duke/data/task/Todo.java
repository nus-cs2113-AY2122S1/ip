package duke.data.task;

/**
 * A Todo task in the task list
 */
public class Todo extends Task {
    /**
     * Constructor that sets the task description with raw values
     *
     * @param description the task description
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the String representation of the Todo task
     *
     * @return the status icon and the task's description
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
