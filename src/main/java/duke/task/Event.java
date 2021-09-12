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

    public String getDateTime() {
        return dateTime;
    }

    /**
     * Return description along with the date time of the event.
     *
     * @return Full description of task.
     */
    @Override
    public String getFullDescription() {
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
}
