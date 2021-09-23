package duke.task;

import java.time.LocalDateTime;

public class Event extends Task {

    protected String type = "[E]";
    protected LocalDateTime at;

    public Event(String desc, LocalDateTime at) {
        super(desc);
        this.at = at;
    }

    @Override
    public String toStorageString() {
        return type + " | " + super.toStorageString() + " | " + at.format(dataFormat);
    }

    @Override
    public String toString() {
        return type + super.toString() + " (at: " + at.format(uiFormat) + ")";
    }
}
