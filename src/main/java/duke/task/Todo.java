package duke.task;

/**
 * The Todo class manages a task that has no date/time attached to it.
 */
public class Todo extends Task {

    /**
     * Initialises a new incomplete Todo.
     *
     * @param description Description of task to be done.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the Todo icon.
     *
     * @return Todo icon.
     */
    @Override
    public String getTaskIcon() {
        return "T";
    }
}
