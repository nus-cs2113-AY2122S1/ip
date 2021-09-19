package duke.tasks;

/**
 * Represents a <code>Task</code> that does not have any specific deadlines or date of occurrence.
 */

public class ToDo extends Task {

    private static final String SYMBOL = "T";
    private static final String SEPARATOR = " | ";

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[" + SYMBOL + "]" + super.toString();
    }

    @Override
    public String toDataStringFormat() {
        return SYMBOL + SEPARATOR + (isDone ? 1 : 0) + SEPARATOR + description + "\n";
    }
}
