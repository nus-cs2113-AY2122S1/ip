package duke.task;

public class ToDo extends Task {
    /**
     * Constructs a ToDo task given description.
     *
     * @param description Description of task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructs a Todo task given description and isDone status.
     *
     * @param description Description of task.
     * @param isDone Status of task.
     */
    public ToDo(String description, boolean isDone) {
        this(description);
        super.taskDone(isDone);
    }

    /**
     * Returns icon of Todo.
     *
     * @return Icon of Todo
     */
    public String getIcon() {
        return "T";
    }

    /**
     * Returns a string representing the ToDo.
     *
     * @return String representing ToDo.
     */
    @Override
    public String toString() {
        return "[" + getIcon() + "]"+ super.toString();
    }
}
