package duke.task;

public class Deadline extends Task {
    /** Task deadline. */
    protected String by;

    /**
     * Initializes the task description and deadline, and
     * sets initial status to "not done".
     *
     * @param description task description
     * @param by task deadline
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Initializes the task description and deadline, and
     * sets initial status according to the given parameter.
     *
     * @param description task description
     * @param by task deadline
     * @param isDone initial status
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String serialize() {
        return "D" + " | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }

    @Override
    public String toString() {
        return "[" + DEADLINE + "]" + super.toString() + " (by: " + by + ")";
    }
}
