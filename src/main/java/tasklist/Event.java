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

    /**
     * Returns additional information which is the type icon and event date & time
     * at the end when toString method is called.
     * @return the type icon followed by the usual toString method of Task and
     * the event date & time at the end
     */
    @Override
    public String toString() {
        return "[" + getType() + "]" + super.toString() + " (at: "
                + at.format(DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm")) + ")";
    }

    /**
     * Adds additional character to support file format when saving event
     * @return adds a comma between 'description' and 'at' strings to follow
     * the CSV format when saving to text file
     */
    @Override
    public String getDescription() {
        return description + "," + at.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
