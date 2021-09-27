package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate eventDate;
    public Event(boolean done, String name, String date) {
        super(done, name);
        this.eventDate = LocalDate.parse(date);
    }

    public Event() {
        super(false, "Nothing");
        this.eventDate = LocalDate.parse("2021-12-31");
    }

    public LocalDate getTaskDate() {
        return eventDate;
    }

    public String getStringEventDate() {
        return eventDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }

    @Override
    public String getPrefix() {
        return "[E]";
    }

    @Override
    public String toString() {
        return getPrefix() + super.toString() + "(at: " + getStringEventDate() + ")";
    }
}
