package duke.tasks;

import java.time.LocalDateTime;

public class Event extends Task{

    protected String atText;
    protected LocalDateTime atDateTime;

    public Event(String description, String atText) {
        super(description);
        this.atText = atText;
        this.atDateTime = null;
    }
    public Event(String description, String atText, LocalDateTime atDateTime) {
        super(description);
        this.atText = atText;
        this.atDateTime = atDateTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + atText + ")";
    }
}
