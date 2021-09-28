package duke.tasks;

import duke.parser.Parser;

import java.time.LocalDateTime;

/**
 * Deadline is a Sub-class that inherits from Task Class
 * A Deadline object is represented by a description of the task and when the Deadline is due by.
 */
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

    @Override
    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
