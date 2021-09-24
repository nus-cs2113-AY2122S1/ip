package duke.task;

/**
 * Represents a task that does not have any date/time attached to it.
 */
public class ToDo extends Task {
    public static final String TASK_TYPE_ICON = "T";

    /**
     * Creates a task with the specified description.
     *
     * @param description Description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String getTaskTypeIcon() {
        return TASK_TYPE_ICON;
    }
}
