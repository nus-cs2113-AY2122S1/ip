package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected String type = "[D]";
    protected LocalDateTime by;

    private final static String DATE_TIME_FORMAT = "dd/MM/yyyy HHmm";
    public final static DateTimeFormatter byFormat = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);

    public Deadline(String desc, LocalDateTime by) {
        super(desc);
        this.by = by;
    }

    @Override
    public String toStorageString() {
        return type + " | " + super.toStorageString() + " | " + by.format(byFormat);
    }

    @Override
    public String toString() {
        return type + super.toString() + " (by: " + by.format(byFormat) + ")";
    }
}
