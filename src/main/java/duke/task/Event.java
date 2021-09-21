package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends TaskWithDateTime {

    public Event(String name, LocalDateTime dateTime) {
        super(name, dateTime);
    }

    @Override
    public String getTaskSymbol() {
        return "[E]";
    }

    @Override
    public String toString() {
        int hours = dateTime.getHour();
        int minutes = dateTime.getMinute();
        return (getTaskSymbol() + getStatusSymbol() + " " + name + " (at: " +
                dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " + hours + ":" + minutes + " )");
    }

    /**
     * returns the Event object as a string in the form to be saved into save file
     * @return Deadline in the form [E][X] Description DT: LocalDateTime
     */
    @Override
    public String toStringForSave() {
        return (getTaskSymbol() + getStatusSymbol() + " " + name + " DT: " + dateTime.toString());
    }
}
