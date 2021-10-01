package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        setBy(by);
    }

    public void setBy(LocalDate byDate) {
        this.by = byDate;
    }

    @Override
    public String getType() {
        return "D";
    }

    @Override
    public LocalDate getWhen() {
        return this.by;
    }

    @Override
    public String toString() {
        return ("[D]" + "[" + getStatusIcon() + "] " + description + "(by: "  + by.format(DateTimeFormatter.ofPattern((Task.DATE_FORMAT))) + ")");
    }
}
