package duke.templar;

/**
 * Defines the type of task known as Event, with a description and a date
 */
public class Event extends Task
{
    String eventDate;

    public Event(String description, String eventDate)
    {
        super(description);
        this.eventDate = eventDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + eventDate + ") ";
    }

}
