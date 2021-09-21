package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate byWhen;

    public Deadline(String taskName, LocalDate byWhen) {
        super(taskName);
        this.byWhen = byWhen;
    }

    public Deadline(String taskName, boolean isDone, LocalDate byWhen) {
        super(taskName, isDone);
        this.byWhen = byWhen;
    }

    public String getByWhen() {
        return byWhen.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (By -> " + getByWhen() + ")";
    }
}
