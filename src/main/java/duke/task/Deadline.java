package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate dueTime;

    public Deadline(String description, LocalDate dueTime) {
        super(description);
        this.dueTime = dueTime;
    }

    @Override
    public String toString() {
        return "[D]" + "[" + getStatusIcon() + "] " + description + " (by: " + dueTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    @Override
    public String getStoreDataString() {
        String checkDone = isDone ? "1" : "0";
        return "D | " + checkDone + " | " + description + " | " + dueTime;
    }
}
