package tasks;

import parser.DateParser;

import java.util.Date;

/**
 * Represents a task with deadline. A Deadline object is represented by a description and date.
 */

public class Deadline extends Task {
    public Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Deadline(String description, Date date) {
        super(description);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateParser.dateTimeToString(date) + ")";
    }

    @Override
    public String toFile() {
        return "D|" + super.toFile() + "|" + DateParser.dateToFile(date);
    }
}
