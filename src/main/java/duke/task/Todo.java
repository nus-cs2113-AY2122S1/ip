package duke.task;
/**
 * Represents a Todo task.
 * <code>Type</code> enum corresponds to respective class.
 */
class Todo extends Task {
    private static final Type type = Type.TODO;

    /**
     * Todo constructor with <code>isDone</code> set to <code>false</code>.
     *
     * @param description Task description.
     */
    Todo(String description) {
        super(description, type);
    }

    /**
     * Todo constructor
     *
     * @param isDone boolean to show whether task is completed.
     * @param description Task description.
     */
    Todo(boolean isDone, String description) {
        super(isDone, description, type);
    }
}
