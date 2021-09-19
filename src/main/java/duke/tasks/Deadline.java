package duke.tasks;

/**
 * Represents a <code>Task</code> that has a specific deadline.
 */

public class Deadline extends Task {

    private static final String SYMBOL = "D";
    private static final String SEPARATOR = " | ";
    private String taskDue;

    public Deadline(String description, String taskDue) {
        super(description);
        this.taskDue = taskDue;
    }

    @Override
    public String toString() {
        return "[" + SYMBOL + "]" + super.toString() + " (by: " + taskDue + ")";
    }

    @Override
    public String toDataStringFormat() {
        return SYMBOL + SEPARATOR + (isDone ? 1 : 0) + SEPARATOR + description + SEPARATOR + taskDue + "\n";
    }
}
