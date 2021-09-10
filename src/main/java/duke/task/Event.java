package duke.task;

public class Event extends Task{
    protected String eventDate;

    public Event(String description, String eventDate, boolean isDone) {
        super(description, isDone);
        this.eventDate = eventDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + eventDate + ")";
    }

    @Override
    public String formatSaveToFile() {
        return "E" + super.formatSaveToFile() + " | " + eventDate;
    }
}
