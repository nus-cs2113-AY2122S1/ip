package duke.data.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected String by;
    protected LocalDate byDate;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, LocalDate byDate) {
        super(description);
        this.byDate = byDate;
        this.by = byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
    public String getBy() {
        return by;
    }

    public LocalDate getByDate() {
        return byDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
