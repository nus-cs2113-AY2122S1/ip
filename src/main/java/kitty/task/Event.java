package kitty.task;

import kitty.Kitty;
import kitty.KittyException;
import kitty.Parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The class <code>Event</code> includes methods that involves tasks of type Event.
 */
public class Event extends Task{
    protected LocalDate eventDate;

    public Event(String taskName, LocalDate eventDate) {
        super(taskName);
        this.eventDate = eventDate;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    /**
     * Adds a task of type Event to list of tasks at hand.
     * @param line line is the String that the user inputs.
     * @throws KittyException If line is of the wrong format for adding a task of Event type.
     */
    public static void addEventTask(String line) throws KittyException {
        if(!Parser.hasEventDate(line)) {
            throw new KittyException("Event formatting is incorrect!");
        } else {
            try {
                // Get Event Name
                String taskName = Parser.getEventTaskName(line);

                // Get Event Date as String
                String eventDateString = Parser.getEventDateString(line);

                // Get Event Date as LocalDate
                LocalDate eventDate = Parser.getTaskDate(eventDateString);

                // Add Event Task
                Kitty.tasks.add(new Event(taskName, eventDate));
            } catch (KittyException e) {
                throw e;
            }
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " [at: " + eventDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + "]";
    }
}
