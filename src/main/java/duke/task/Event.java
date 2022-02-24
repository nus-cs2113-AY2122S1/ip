package duke.task;

import java.time.LocalDate;
import java.time.format.*;

public class Event extends Task {

    protected LocalDate at;

    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " /at " + this.at.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public LocalDate getAt() {
        return at;
    }

    public void setAt(LocalDate at) {
        this.at = at;
    }

}
