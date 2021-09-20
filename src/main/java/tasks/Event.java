package tasks;

import java.time.LocalDate;
import java.time.LocalTime;

public class Event extends Task {

    protected LocalDate date;
    protected LocalTime time;

    public Event(String description, String at) {
        super(description);
        String[] when = at.trim().split(" ");
        this.date = TimeHandler.getDate(when[0].trim());
        this.time = TimeHandler.getTime(when[1].trim());
    }

    public String toString() {
        return "[E]" + super.toString() + " (at: " + TimeHandler.formatDate(date)
                + " " + TimeHandler.formatTime(time) + ')';
    }
}
