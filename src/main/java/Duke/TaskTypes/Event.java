package Duke.TaskTypes;

import Duke.BackEnd.DukeParser;
import java.time.LocalDateTime;
import static Duke.UI.DukeConstants.FORMAT_DATE_TIME_OUTPUT;

public class Event extends Task{

    protected LocalDateTime at;

    /**
     * Constructor for event class
     *
     * @param description event description
     * @param at event at description
     */
    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    public LocalDateTime getAt() {
        return at;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (at: " + DukeParser.convertDateTimeToString(at, FORMAT_DATE_TIME_OUTPUT) + ")";
    }

    @Override
    public String getType() {
        return "E";
    }
}
