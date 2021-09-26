package tasklist;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime at;

    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
        this.type = "E";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm")) + ")";
    }

    //at.format(DateTimeFormatter.ofPattern("MMM dd, yyyy"))

    @Override
    public String getDescription() {
        return description + "," + at.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
