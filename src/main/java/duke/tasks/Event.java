package duke.tasks;

/**
 * Class representing event tasks.
 * Extended from Task.
 * These tasks require additional {@code at} parameter signifying time of event.
 */
public class Event extends Task{

    protected String at;

    /**
     * Initializes new event task with description and time.
     * @param description description of task from user input
     * @param at time of task from user input
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns event task in its string format.
     * @return string format of event task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
