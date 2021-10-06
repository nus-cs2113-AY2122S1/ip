package duke.task;

public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Re-formats the task type, status, description and event time into the proper format for the txt file.
     *
     * @return task details in format for txt file.
     */
    public String getTaskDetailsInFileFormat() {
        return "E | " + isDone + " | " + description + " | " + at;
    }

    /**
     * Overwrites the default method with a custom print message instead.
     *
     * @return the task type, status, details and event time.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
