package duke.task;

/**
 * The Event class manages a task that start at a specific time and ends at a specific time.
 */
public class Event extends Task {

    /* Timestamp of occurrence */
    private String dateTime;

    /**
     * Initialise a new incomplete task with time of occurrence.
     *
     * @param description Description of task to be done.
     * @param dateTime    When the task is occurring at.
     */
    public Event(String description, String dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    @Override
    public String getDescription() {
        return String.format("%s (at: %s)", super.getDescription(), dateTime);
    }

    /**
     * Return the Event icon.
     *
     * @return Event icon.
     */
    @Override
    public String getTaskIcon() {
        return "E";
    }

    @Override
    public String toString() {
        return String.format("%s | %s", super.toString(), dateTime);
    }
}
