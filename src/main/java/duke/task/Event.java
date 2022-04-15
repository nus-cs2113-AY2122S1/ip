package duke.task;

import java.time.format.DateTimeFormatter;

/**
 * The Event class manages a task that start at a specific time and ends at a specific time.
 */
public class Event extends Deadline {

    /**
     * Initialises a new incomplete task with time of occurrence.
     *
     * @param description Description of task to be done.
     * @param dateTime    When the task is occurring at.
     */
    public Event(String description, String dateTime) {
        super(description, dateTime);
    }

    /**
     * Returns description along with the date time of the event.
     *
     * @return Full description of task.
     */
    @Override
    public String getFullDescription() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        String dateTime = String.format("%s %s", date.format(formatter), time);
        return String.format("%s (at: %s)", super.getDescription(), dateTime);
    }

    /**
     * Returns the Event icon.
     *
     * @return Event icon.
     */
    @Override
    public String getTaskIcon() {
        return "E";
    }
}
