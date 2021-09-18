package tasks;

import parser.DateParser;

import java.util.Date;

public class Event extends Task {

    public Date at;

    public Date getAt() {
        return at;
    }

    public void setAt(Date at) {
        this.at = at;
    }

    public Event(String description, Date at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + DateParser.dateTimeToString(at) + ")";
    }

    @Override
    public String toFile() {
        return "E|" + super.toFile() + "|" + DateParser.dateToFile(at);
    }
}
