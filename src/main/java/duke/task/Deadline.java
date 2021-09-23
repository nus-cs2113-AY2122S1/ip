package duke.task;

import java.time.LocalDateTime;

public class Deadline extends Task {

    protected String type = "[D]";
    protected LocalDateTime by;

    public Deadline(String desc, LocalDateTime by) {
        super(desc);
        this.by = by;
    }

    @Override
    public String toStorageString() {
        return type + " | " + super.toStorageString() + " | " + by.format(dataFormat);
    }

    @Override
    public String toString() {
        return type + super.toString() + " (by: " + by.format(uiFormat) + ")";
    }
}
