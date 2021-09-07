package duke.task;

/**
 * This class is used for tasks that need to be done before a specific date/time.
 * E.g: submit iP by thurs 2359
 */
public class Deadline extends Task {
    protected String deadline;
    protected static final String DEADLINE_LOGO = "[D]";

    public Deadline(String description, String deadline) {
        super(description);
        setDeadline(deadline);
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    /**
     * Returns Deadline task formatted in the form "[D][ ] description (by: deadline)"
     *
     * @return Formatted Event task string
     */
    @Override
    public String toString() {
        return DEADLINE_LOGO + super.toString() + " (by: " + deadline + ")";
    }
}
