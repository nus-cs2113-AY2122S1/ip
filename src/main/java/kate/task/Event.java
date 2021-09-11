package kate.task;

public class Event extends Task {
    protected String timeFrame;
    private static final String EVENT_CHECKBOX = "[E]";

    public Event(String description, String timeFrame) {
        this.description = description;
        this.isDone = false;
        this.timeFrame = timeFrame;
    }

    /**
     * Retrieves the event information to be written to a file
     *
     * @return Formatted String of Event information
     */
    public String getTaskInfoForFile() {
        return EVENT_CHECKBOX + " | " + isDone + " | " + description + " | " + timeFrame;
    }

    /**
     * Retrieves Task information and additional event information
     *
     * @return String description of task and additional event information
     */
    @Override
    public String getTaskInfo() {
        return EVENT_CHECKBOX + super.getTaskInfo()
                + " (at: " + timeFrame + ")";
    }
}
