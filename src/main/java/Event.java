public class Event extends Task {
    protected String description;
    protected String timeFrame;
    private static final String EVENT_CHECKBOX = "[E]";

    public Event(String description, String timeFrame) {
        super(description);
        this.timeFrame = timeFrame;
    }

    public String getTimeFrame() {
        return timeFrame;
    }

    @Override
    public String printTaskInfo() {
        return EVENT_CHECKBOX + super.printTaskInfo()
                + " (at: " + timeFrame + ")";
    }
}
