package duke.task;

public class Event extends Task {

    private String heldDate;

    /**
     * Class event constructor.
     *
     * @param taskName Name of the task.
     * @param heldDate Date when the event is held.
     */
    public Event(String taskName, String heldDate) {
        super(taskName);
        this.heldDate = heldDate;
    }

    /**
     * Gets the description and held date of the task.
     *
     * @return String containing task description and held date.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + heldDate + ")";
    }

    /**
     * Formats task details to write onto file.
     *
     * @return String containing task details for file format
     */
    @Override
    public String toFileString() {
        int stringIsDone = isDone ? 1 : 0;
        return "E" + DELIMITER + stringIsDone + DELIMITER + taskName + DELIMITER + heldDate;
    }
}
