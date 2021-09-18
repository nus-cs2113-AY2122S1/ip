package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime dateTime;

    public Deadline(String name, LocalDateTime dateTime) {
        super(name);
        this.dateTime = dateTime;
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

    @Override
    public String toStringForSave() {
        return (getTaskSymbol() + getStatusSymbol() + " " + name + " DT: " + dateTime.toString());
    }
}
