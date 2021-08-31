public class Event extends Task {
    private static final String EVENT_ITEM = "[E] ";

    private String timeOfEvent;

    public Event(String description, String timeOfEvent) {
        super(description);
        this.timeOfEvent = timeOfEvent;
    }

    public String getTimeOfEvent() {
        return timeOfEvent;
    }

    public void setTimeOfEvent(String timeOfEvent) {
        this.timeOfEvent = timeOfEvent;
    }

    public String printAt() {
        return " (at: " + timeOfEvent + ")";
    }

    @Override
    public String toString() {
        return EVENT_ITEM + super.toString() + printAt();
    }
}
