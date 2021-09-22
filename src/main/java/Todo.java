/**
 * A type of task that stores a description of the task
 */
public class Todo extends Task {
    public static final char TASK_TYPE_TODO = 'T';

    public Todo(String description) {
        super(description, TASK_TYPE_TODO);
    }

    public Todo(String description, boolean isDone) {
        super(description, TASK_TYPE_TODO, isDone);
    }

    @Override
    public String toString() {
        return "[" + taskType + "][" + getStatusIcon() + "] "
                + description;
    }
}
