package task.type;

public class Todo extends Task {
    public static final String type = "T";
    public Todo(String description) {
        super(description);
    }
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Prints the todo task in the display format
     *
     * @return todo task in the correct format to be printed.
     */
    @Override
    public String toString() {
        return "[" + type + "]" + super.toString();
    }

    /**
     * Returns type of task
     *
     * @return 'T' which is the task type for todo
     */
    @Override
    public String getTaskType() {
        return type;
    }
}