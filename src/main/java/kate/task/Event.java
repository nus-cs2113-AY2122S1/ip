package kate.task;

public class Event extends Task {
    protected String timeFrame;
    private static final String EVENT_CHECKBOX = "[E]";

    public Event(String description, String timeFrame) {
        super(description);
        this.timeFrame = timeFrame;
    }

    public String getTaskInfoForFile() {
        return EVENT_CHECKBOX + " | " + isDone + " | " + description + " | " + timeFrame;
    }

    @Override
    public String getTaskInfo() {
        return EVENT_CHECKBOX + super.getTaskInfo()
                + " (at: " + timeFrame + ")";
    }
}
