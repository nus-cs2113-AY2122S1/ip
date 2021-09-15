package duke.task;

/**
 * Represents a task that does not have any date/time attached to it.
 */
public class Todo extends Task {
    public static final String TASK_TYPE_ICON = "T";

    /**
     * Creates a task with the specified description.
     *
     * @param description Description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getTaskTypeIcon() {
        return TASK_TYPE_ICON;
    }
}
