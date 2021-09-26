package duke.processes.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {
    protected static DateTimeFormatter format2 = DateTimeFormatter.ofPattern("d MMM yyyy, HHmm");

    public Deadlines(String description, LocalDateTime d, String date) {

        super(description, d);
    }

    @Override
    public String toString() {

        return description;
    }

    @Override
    public String getTaskType() {

        return "deadline";
    }

    @Override
    public String getTaskID() {

        return "D";
    }

    @Override
    public String getDate() {

        return date.format(format2);
    }
}
