package duke.tasks;

/**
 * Represents a <code>Task</code> that does not have any specific deadlines or date of occurrence.
 */
public class ToDo extends Task {

    private static final String SYMBOL = "T";
    private static final String SEPARATOR = " | ";

    /**
     * Constructs a <code>ToDo</code> object with all its relevant information such as description.
     *
     * @param description <code>String</code> description of the task
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[" + SYMBOL + "]" + super.toString();
    }

    @Override
    public String getDate() {
        return "";
    }

    @Override
    public String toDataStringFormat() {
        return SYMBOL + SEPARATOR + (isDone ? 1 : 0) + SEPARATOR + description + "\n";
    }
}
