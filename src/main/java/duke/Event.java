package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate at;

    public Event(String description, LocalDate at) {
        super(description);
        setAt(at);
    }

    public void setAt(LocalDate atDate) {
        this.at = atDate;
    }

    @Override
    public String getType()  {
        return "E";
    }

    @Override
    public LocalDate getWhen() {
        return this.at;
    }

    @Override
    public String toString() {
        return ("[E]" + "[" + getStatusIcon() + "] " + description + "(at: " + at.format(DateTimeFormatter.ofPattern((Task.DATE_FORMAT))) + ")");
    }

}
