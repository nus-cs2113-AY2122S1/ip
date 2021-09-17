package duke.task;

/**
 * The Deadline class manages a task that need to be done before a specific date/time.
 */
public class Deadline extends Task {

    /* Timestamp of deadline */
    private String dateTime;

    /**
     * Initialise a new incomplete task with a deadline.
     *
     * @param description Description of task to be done.
     * @param dateTime    When the task need to be done by.
     */
    public Deadline(String description, String dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    /**
     * Return the description along with the date time of the deadline.
     *
     * @return Full description of task.
     */
    @Override
    public String getFullDescription() {
        return String.format("%s (by: %s)", super.getDescription(), dateTime);
    }

    public String getDateTime() {
        return dateTime;
    }

    /**
     * Return the Deadline icon.
     *
     * @return Deadline icon.
     */
    @Override
    public String getTaskIcon() {
        return "D";
    }
}
