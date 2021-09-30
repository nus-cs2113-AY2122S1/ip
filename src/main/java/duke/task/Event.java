package duke.task;

public class Event extends Task {
    private static final TaskType taskType = TaskType.EVENT;
    private final String timeslot;

    /**
     * Event constructor
     *
     * @param title Title of event
     * @param timeslot Time of event
     */
    public Event(String title, String timeslot) {
        super(title);
        this.timeslot = timeslot;
    }

    /**
     * @return Type of Task
     */
    @Override
    public TaskType getTaskType() {
        return taskType;
    }

    /**
     * @return String representation of Event for display
     */
    @Override
    public String toString() {
        String SYMBOL = "E";
        return "[" + SYMBOL + "]" + super.toString() + " (at: " + timeslot + ")";
    }

    /**
     * @return Time of Event
     */
    @Override
    public String getTime() {
        return timeslot;
    }
}
