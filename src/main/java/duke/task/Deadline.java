package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected String by;
    protected LocalDate formalFormDate = null;
    protected boolean isValid = false;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        formalFormDate = LocalDate.parse(by);
        if (formalFormDate != null) {
            isValid = true;
        }
    }

    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        if (isValid) {
            return "[D]" + super.toString() + " (by: " + formalFormDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
        }
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
