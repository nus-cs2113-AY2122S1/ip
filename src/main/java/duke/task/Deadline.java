package duke.task;

import java.time.LocalDate;

public class Deadline extends Task {

    /** Date and time of deadline. */
    private LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        super.setType("D");
        this.by = by;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " (by: " + getBy() + ")";
    }

    public LocalDate getBy() {
        return by;
    }
}
