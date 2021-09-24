package duke.task;

import java.time.LocalDateTime;

public class Deadline extends Task {

    protected String type = DEADLINE_ICON;
    protected LocalDateTime by;

    public Deadline(String desc, LocalDateTime by) {
        super(desc, DEADLINE_ICON);
        this.by = by;
    }

    @Override
    public LocalDateTime getDateTime() {
        return this.by;
    }


    @Override
    public String toStorageString() {
        return type + PADDED_DATA_SEP + super.toStorageString() + PADDED_DATA_SEP + by.format(dataFormat);
    }

    @Override
    public String toString() {
        return type + super.toString() + " (by: " + by.format(uiFormat) + ")";
    }
}
