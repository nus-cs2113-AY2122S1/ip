package shikabot.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{

    private LocalDate at;

    public Event(String name, LocalDate at) {
        super(name);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    public String getType() {
        return "E";
    }

    public LocalDate getAtBy() {
        return at;
    }

}
