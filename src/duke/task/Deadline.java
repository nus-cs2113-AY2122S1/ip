package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    public static final String DEADLINE_ICON = "[D]";
    private LocalDate dueDate;

    public Deadline(String taskName, LocalDate dueDate) {
        super(taskName);
        this.dueDate = dueDate;
    }

    @Override
    public String exportTask() {
        return "D|" + super.getStatus() + "|" + super.toString() + "|" + dueDate + System.lineSeparator();
    }

    @Override
    public String toString() {
        return DEADLINE_ICON + super.toString() + " (by: " + dueDate + ")";
    }
}
