package kitty.task;

import kitty.Kitty;
import kitty.KittyException;
import kitty.Parser;
import kitty.io.IO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    protected LocalDate eventDate;

    public Event(String taskName, LocalDate eventDate) {
        super(taskName);
        this.eventDate = eventDate;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public static void addEventTask(String line) throws KittyException {
        if(!Parser.hasEventDate(line)) {
            throw new KittyException("Event formatting is incorrect!");
        } else {
            try {
                // Get Event Name
                String taskName = Parser.getEventTaskName(line);

                // Get Event Date as String
                String eventDateString = Parser.getEventDate(line);

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
