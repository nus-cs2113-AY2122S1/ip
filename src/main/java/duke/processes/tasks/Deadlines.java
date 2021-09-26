package duke.processes.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {
    protected LocalDateTime date;
    protected static DateTimeFormatter format1 = DateTimeFormatter.ofPattern("dd/MM/yyyy-HHmm");
    protected static DateTimeFormatter format2 = DateTimeFormatter.ofPattern("d MMM yyyy, HHmm");

    public Deadlines(String description, LocalDateTime d, String date) {
        super(description, d);
        this.date = LocalDateTime.parse(date, format1);
    }

    public String toString() {
        return description;
    }

    public String getTaskType() {
        return "deadline";
    }

    public String getTaskID() {
        return "D";
    }

    public String getDate() {
        return date.format(format2);
    }
}
