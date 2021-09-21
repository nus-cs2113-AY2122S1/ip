package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends TaskWithDateTime {

    public Deadline(String name, LocalDateTime dateTime) {
        super(name, dateTime);
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public String getTaskSymbol() {
        return "[D]";
    }

    @Override
    public String toString() {
        int hours = dateTime.getHour();
        int minutes = dateTime.getMinute();
        return (getTaskSymbol() + getStatusSymbol() + " " + name + " (by: " +
                dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " + hours + ":" + minutes + " )");
    }

    /**
     * returns the Deadline object as a string in the form to be saved into save file
     * @return Deadline in the form [D][X] Description DT: LocalDateTime
     */
    @Override
    public String toStringForSave() {
        return (getTaskSymbol() + getStatusSymbol() + " " + name + " DT: " + dateTime.toString());
    }
}
