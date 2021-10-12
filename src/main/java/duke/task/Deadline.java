package duke.task;

/**
 * Represents a {@code Deadline} task (Subclass of {@code Task}).
 */
public class Deadline extends Task {

    /**
     * Represents the deadline of the task.
     */
    protected String by;

    /**
     * Constructor that takes in {@code description} and {@code by} only.
     *
     * @param description description of the Deadline task.
     * @param by          deadline of the Deadline task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructor that takes in {@code description}, {@code isDone} and {@code by}.
     *
     * @param description description of the Deadline task.
     * @param isDone      whether the Deadline task is done.
     * @param by          deadline of the Deadline task.
     */
    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Gets full task description of a {@code Deadline} task and the task type symbol.
     *
     * @return a String containing the task type symbol, description and isDone status.
     */
    @Override
    public String getTaskDescription() {
        return "[D]" + super.getTaskDescription() + " (by: " + by + ")";
    }

    /**
     * Gets task information that matches the format of the {@code Deadline} tasks stored in duke.txt.
     *
     * @return a String in the format of duke.txt {@code Deadline} task entry.
     */
    @Override
    public String getTaskFileFormat() {
        String isDoneString = "0";

        if (isDone) {
            isDoneString = "1";
        }

        return "D | " + isDoneString + " | " + description + " | " + by;
    }

}
