package duke;

public class Event extends Task {

    /**
     * Stores the event time of the task
     */
    protected String at;

    /**
     * Constructor to initialize task description and event time.
     *
     * @param description Description stores the description of the task.
     * @param at          At stores the event time of the task.
     */
    Event(String description, String at) {
        super(description);
        this.at = at;
        this.taskType = TaskType.EVENT;
    }

    /**
     * returns the string in a particular format.
     *
     * @return Task description and event time in a particular format.
     */
    @Override
    public String toString() {
        return ("[E]" + super.toString() + " (at:" + this.at + ")");
    }
}
