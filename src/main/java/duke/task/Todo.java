package duke.task;

/**
 * Inherits the <code>Task</code> class to represent a Todo task.
 */
public class Todo extends Task {
    /**
     * Constructs an unfinished Todo task with a description.
     *
     * @param description Description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a Todo task with a description and an option to choose whether the task is done.
     *
     * @param description Description of the task.
     * @param isDone Indicates whether the task is done or not.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
        this.isDone = isDone;
    }

    /**
     * Overrides the default toString method to display the task in a more suitable format.
     *
     * @return A string showing status of the task and its description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
