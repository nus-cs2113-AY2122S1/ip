package duke.task;

public class Deadline extends Task {
    private String by;

    /**
     * Constructor to create a Deadline task.
     * Constructor takes in the description and time when the deadline is due.
     * @param description Description of the deadline type task.
     * @param by Date when the deadline is due.
     */
    public Deadline(String description, String by) {
        super(description, "D");
        setBy(by);
    }

    /**
     * Retrieves the date of the deadline.
     * @return String representing the deadline.
     */
    public String getBy() {
        return by;
    }

    /**
     * Sets the deadline of the task
     * @param deadline Date of the deadline
     */
    public void setBy(String deadline) {
        this.by = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
