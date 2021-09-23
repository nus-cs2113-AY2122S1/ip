package duke.task;

/**
 * Class representing an Event task
 */
public class Events extends Task{
    protected String eventLocation;

    /**
     * Constructor for an Event task initialises the Event task's description and location
     * @param description Description of the event
     * @param eventLocation Location of the event
     */
    public Events(String description, String eventLocation) {
        super(description);
        this.eventLocation = eventLocation;
    }

    /**
     * Gets the current Event task's description
     * @return Current event's location
     */
    public String getEventLocation() {
        return eventLocation;
    }

    /**
     * Converts the current Event task into a String
     * @return Event task in the form of a String
     */
    @Override
    public String toString() {
        if (description.contains("|")) {
            description = description.replace("|", ",");
        }
        return "[E]" + getStatusIcon() + description + " (at:" + eventLocation + ")";
    }

    /**
     * Converts the current Event task object into a format that can be stored in a CSV file.
     * @return Event task in the form of a comma separated value
     */
    @Override
    public String convertToCSV() {
        if (description.contains(",")) {
            description = description.replace(",", "|");
        }
        return ("E," + getStatusIcon() + "," + description + "," + eventLocation);
    }
}
