package duke.task;

public class Events extends Task{
    protected String eventLocation;

    public Events(String description, String eventLocation) {
        super(description);
        this.eventLocation = eventLocation;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    @Override
    public String toString() {
        if (description.contains("|")) {
            description = description.replace("|", ",");
        }
        return "[E]" + getStatusIcon() + description + " (at:" + eventLocation + ")";
    }

    @Override
    public String convertToCSV() {
        if (description.contains(",")) {
            description = description.replace(",", "|");
        }
        return ("E," + getStatusIcon() + "," + description + "," + eventLocation);
    }
}
