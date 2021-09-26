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

    @Override
    public String getTaskDescription() {
        return "[E]" + super.getTaskDescription() + " (at: " + at + ")";
    }

    @Override
    public String getTaskFileFormat() {
        String isDoneString = "0";

        if (isDone) {
            isDoneString = "1";
        }

        return "E | " + isDoneString + " | " + description + " | " + at;
    }

}
