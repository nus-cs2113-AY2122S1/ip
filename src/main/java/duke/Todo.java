package duke;

/**
 * A type of task that stores a description of the task
 */
public class Todo extends Task {
    public static final char TASK_TYPE_TODO = 'T';

    /**
     * Constructor of Todo Task from console
     *
     * @param description
     */
    public Todo(String description) {
        super(description, TASK_TYPE_TODO);
    }

    /**
     * Constructor of Todo Task from file
     *
     * @param description
     * @param isDone
     */
    public Todo(String description, boolean isDone) {
        super(description, TASK_TYPE_TODO, isDone);
    }

    /**
     * Convert event task to string upon printing on console
     *
     * @return string of todo task
     */
    @Override
    public String toString() {
        return "[" + taskType + "][" + getStatusIcon() + "] "
                + description;
    }
}
