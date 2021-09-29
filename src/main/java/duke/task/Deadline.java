package duke.task;

public class Deadline extends Task {
    protected String by;
    protected String type;

    /**
     * Constructor for Deadline.
     *
     * @param isDone Whether task has been done or not.
     * @param taskName Name of task.
     * @param by Deadline of the task.
     */
    public Deadline(boolean isDone, String taskName, String by) {
        super(taskName);
        this.isDone = isDone;
        this.by = by;
        this.type = "D";
    }

    /**
     * Returns information about task in a sensible form.
     *
     * @return Information about task in a string.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Returns a String of information about task in format readable by load() method.
     *
     * @param DELIMITER Delimiter separating information in return String.
     * @return String of task information.
     */
    @Override
    public String toSaveFile(String DELIMITER) {
        return "D" + super.toSaveFile(DELIMITER) + DELIMITER + by;
    }
}
