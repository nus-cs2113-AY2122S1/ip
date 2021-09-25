package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected String at;
    protected LocalDate formalFormDate = null;
    protected boolean isValid = false;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        formalFormDate = LocalDate.parse(at);
        if (formalFormDate != null) {
            isValid = true;
        }
    }

    public String getAt() {
        return at;
    }

    @Override
    public String toString() {
        if (isValid) {
            return "[E]" + super.toString() + " (at: " + formalFormDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
        }
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
