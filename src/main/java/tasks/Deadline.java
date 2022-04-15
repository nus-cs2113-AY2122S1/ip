package tasks;

/**
 * Represents a Deadline Task in the task list.
 */
public class Deadline extends Task {

    public static final String INITIAL = "D";

    protected String deadline;

    public String getDeadline() {
        return deadline;
    }

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return ("[D][" + getStatusIcon() + "] " + description + " (by:" + deadline + ")");
    }
}