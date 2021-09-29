package duke.task;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Re-formats the task type, status, description and deadline into the proper format for the txt file.
     *
     * @return task details in format for txt file.
     */
    public String getTaskDetailsInFileFormat() {
        return "D | " + isDone + " | " + description + " | " + by;
    }

    /**
     * Overwrites the default method with a custom print message instead.
     *
     * @return the task type, status, details and deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
