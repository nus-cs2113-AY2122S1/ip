package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yy");
    private LocalDate byDate;

    public Deadline(String taskName, LocalDate byDate) {
        super(taskName);
        this.byDate = byDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " " + "(by: " + byDate.format(formatter) + ")";
    }

    @Override
    public String getStorageString() {
        String c = isCompleted ? "1" : "0";
        return "D | " + c + " | " + taskName + " | " + byDate.toEpochDay();
    }
}
