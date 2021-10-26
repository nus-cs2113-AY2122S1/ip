package duke.templar;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Defines the type of task known as Event, with a description and a date
 */
public class Event extends Task
{
    LocalDateTime eventDate;

    public Event(String description, LocalDateTime eventDate)
    {
        super(description);
        this.eventDate = eventDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + eventDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy h.mma")) + ") ";
    }

}
