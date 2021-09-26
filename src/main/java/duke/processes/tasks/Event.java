package duke.processes.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Event extends Task {
    protected static DateTimeFormatter format2 = DateTimeFormatter.ofPattern("d MMM yyyy, HHmm");

    public Event(String description, LocalDateTime d, String date) {

        super(description, d);
    }

    @Override
    public String toString() {

        return description;
    }

    @Override
    public String getTaskType() {

        return "event";
    }

    @Override
    public String getTaskID() {

        return "E";
    }

    @Override
    public String getDate() {

        return date.format(format2);
    }
}
