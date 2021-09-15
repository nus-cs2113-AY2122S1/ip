public class Event extends Task {
    private String eventDate;
    public static final char TASK_TYPE_EVENT = 'E';

    public Event(String description, String eventDate) {
        super(description, TASK_TYPE_EVENT);
        this.eventDate = eventDate;
    }

    public Event(String description, boolean isDone, String eventDate) {
        super(description, TASK_TYPE_EVENT, isDone);
        this.eventDate = eventDate;
    }

    @Override
    public String toString() {
        return "[" + taskType + "][" + getStatusIcon() + "] "
                + description + " (at: " + eventDate + ")";
    }

    public String getDate() {
        return eventDate;
    }
}
