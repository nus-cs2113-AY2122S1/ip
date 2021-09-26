package duke.processes.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Event extends Task {
    //protected LocalDateTime date;
    //protected static DateTimeFormatter format1 = DateTimeFormatter.ofPattern("dd/MM/yyyy-HHmm");
    protected static DateTimeFormatter format2 = DateTimeFormatter.ofPattern("d MMM yyyy, HHmm");

    public Event(String description, LocalDateTime d, String date) {
        super(description, d);
        //this.date = LocalDateTime.parse(date, format1);
    }

    public String toString() {
        return description;
    }

    public String getTaskType() {
        return "event";
    }

    public String getTaskID() {
        return "E";
    }

    public String getDate() {
        return date.format(format2);
    }
}
