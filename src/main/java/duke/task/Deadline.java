package duke.task;

/**
 * Deadline task that has a due date/time
 */
public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the deadline of the task
     * @return due date/time of task
     */
    public String getBy() {
        return this.by;
    }

    /**
     * Set the string, by
     * @param by the deadline of task
     */
    public void setBy(String by) {
        this.by = by;
    }

    /**
     * Returns the char that represents deadlines tasks
     * @return char for identifying deadlines
     */
    public String getCode() {
        return "D";
    }

    /**
     * To print deadline task in a certain format
     * @return String that shows the information and status of deadline task
     */
    @Override
    public String toString() {
        return "[D][" + super.getStatusIcon() + "] " + super.getDescription() +
                " (by: " + this.getBy() + ")";
    }
}
