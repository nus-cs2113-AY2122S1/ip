package duke.tasks;

import duke.parser.Parser;
import java.time.LocalDateTime;

public class Event extends Task {
    protected LocalDateTime dateTime;

    public Event(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + Parser.getFormattedDateTime(dateTime) + ")";
    }

    @Override
    public String toFileFormat() {
        return "E|" + super.toFileFormat() + "|" + dateTime;
    }

    public String getDateString() {
        return Parser.getFormattedDate(dateTime.toLocalDate());
    }
}
