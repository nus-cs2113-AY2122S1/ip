package duke.task;

/**
 * Represents tasks which are deadlines
 */
public class Deadline extends Task {
    
    protected String deadline;

    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    /**
     * Sets the deadline of the task
     *
     * @param deadline the deadline of the task
     */
    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    /**
     * Returns the deadline of the task
     *
     * @return the deadline of the task
     */
    public String getDeadline() {
        return this.deadline;
    }

    /**
     * Overrides default toString method with the custom print message
     *
     * @return the custom print message
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}
