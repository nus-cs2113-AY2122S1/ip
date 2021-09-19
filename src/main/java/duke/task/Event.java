package duke.task;

import duke.parser.Parser;

import java.time.LocalDate;
import java.util.Objects;

public class Event extends Task {
    private LocalDate at;

    public Event(String description, String at) {
        super(description, "E");
        setAt(at);
    }

    public void setAt(String at) {
        this.at = Parser.parseLocalDate(at);
    }

    public LocalDate getAt() {
        return this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + Parser.formatLocalDate(at) + ")";
    }
}
