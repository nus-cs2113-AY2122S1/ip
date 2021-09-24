package duke.task;

import java.time.LocalDateTime;

public class Event extends Task {

    protected String type = EVENT_ICON;
    protected LocalDateTime at;

    public Event(String desc, LocalDateTime at) {
        super(desc, EVENT_ICON);
        this.at = at;
    }

    @Override
    public LocalDateTime getDateTime() {
        return this.at;
    }

    @Override
    public String toStorageString() {
        return type + PADDED_DATA_SEP + super.toStorageString() + PADDED_DATA_SEP + at.format(dataFormat);
    }

    @Override
    public String toString() {
        return type + super.toString() + " (at: " + at.format(uiFormat) + ")";
    }
}
