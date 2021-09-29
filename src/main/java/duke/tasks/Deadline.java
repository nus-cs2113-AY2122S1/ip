package duke.tasks;

/**
 * Class representing deadline tasks.
 * Extended from Task.
 * These tasks require additional {@code by} parameter signifying completion deadline of the task.
 */
public class Deadline extends Task{

    protected String by;

    /**
     * Initializes new deadline task with description and completion deadline.
     * @param description description of task from user input
     * @param by completion deadline of task from user input
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns deadline task in its string format.
     * @return string format of deadline task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
