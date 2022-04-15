package duke.task;

/**
 * Represents a Todo task.
 */
public class Todo extends Task{

    private static final String ICON_TODO = "T";

    /**
     * Creates a Todo object.
     *
     * @param description The description of the Todo Task.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getTaskIcon() {
        return ICON_TODO;
    }

    @Override
    public String toString() {
        return "["  + this.getTaskIcon() +"]" + super.toString();
    }
}
