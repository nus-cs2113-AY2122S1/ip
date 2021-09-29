package duke.task;

public class Event extends Task {
    protected String at;
    protected String type;

    /**
     * Constructor for Event.
     *
     * @param isDone Whether task has been done or not.
     * @param taskName Name of task.
     * @param at Deadline of the task.
     */
    public Event(boolean isDone, String taskName, String at) {
        super(taskName);
        this.isDone = isDone;
        this.at = at;
        this.type = "E";
    }


    /**
     * Returns information about task in a sensible form.
     *
     * @return Information about task in a string.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    /**
     * Returns a String of information about task in format readable by load() method.
     *
     * @param DELIMITER Delimiter separating information in return String.
     * @return String of task information.
     */
    @Override
    public String toSaveFile(String DELIMITER) {
        return "E" + super.toSaveFile(DELIMITER) + DELIMITER + at;
    }
}
