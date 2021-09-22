package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{

    protected String at;
    protected LocalDate atDate;
    protected LocalTime atTime;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        String[] dateAndTime = at.split(" ");
        this.atDate = LocalDate.parse(dateAndTime[0]);
        this.atTime = LocalTime.parse(dateAndTime[1]);
    }

    @Override
    public String toString() {
        String properDate = atDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String properTime = atTime.format(DateTimeFormatter.ofPattern("ha"));
        return "[E][" + getStatusIcon() + "] " + super.toString() + " (at: " + properDate + " " + properTime + ")";
    }
}
