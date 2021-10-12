package duke.task;

/**
 * Represents a {@code Event} task (Subclass of {@code Task}).
 */
public class Event extends Task {

    /**
     * Represents the occasion of the task.
     */
    protected String at;

    /**
     * Constructor that takes in {@code description} and {@code by} only.
     *
     * @param description description of the Event task.
     * @param at          deadline of the Event task.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Constructor that takes in {@code description}, {@code isDone} and {@code by}.
     *
     * @param description description of the Event task.
     * @param isDone      whether the Event task is done.
     * @param at          deadline of the Event task.
     */
    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.at = at;
    }

    /**
     * Gets full task description of a {@code Event} task and the task type symbol.
     *
     * @return a String containing the task type symbol, description and isDone status.
     */
    @Override
    public String getTaskDescription() {
        return "[E]" + super.getTaskDescription() + " (at: " + at + ")";
    }

    /**
     * Gets task information that matches the format of the {@code Event} tasks stored in duke.txt.
     *
     * @return a String in the format of duke.txt {@code Event} task entry.
     */
    @Override
    public String getTaskFileFormat() {
        String isDoneString = "0";

        if (isDone) {
            isDoneString = "1";
        }

        return "E | " + isDoneString + " | " + description + " | " + at;
    }

}
