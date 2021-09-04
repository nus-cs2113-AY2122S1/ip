package kate.task;

public class Event extends Task {
    protected String timeFrame;
    private static final String EVENT_CHECKBOX = "[E]";

    public Event(String description, String timeFrame) {
        super(description);
        this.timeFrame = timeFrame;
    }

    @Override
    public String printTaskInfo() {
        return EVENT_CHECKBOX + super.printTaskInfo()
                + " (at: " + timeFrame + ")";
    }
}
