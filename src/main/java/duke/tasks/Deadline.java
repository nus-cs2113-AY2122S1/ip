package duke.tasks;

import duke.parser.Parser;

import java.time.LocalDateTime;

public class Deadline extends Task {
    protected LocalDateTime dateTime;

    public Deadline(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + Parser.getFormattedDateTime(dateTime) + ")";
    }

    @Override
    public String toFileFormat() {
        return "D|" + super.toFileFormat() + "|" + dateTime;
    }

    public String getDateString() {
        return Parser.getFormattedDate(dateTime.toLocalDate());
    }
}
