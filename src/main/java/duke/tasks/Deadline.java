package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private static final String SYMBOL = "D";
    private static final String SEPARATOR = " | ";
    private LocalDateTime taskDue;

    public Deadline(String description, LocalDateTime taskDue) {
        super(description);
        this.taskDue = taskDue;
    }

    @Override
    public String toString() {
        return "[" + SYMBOL + "]" + super.toString() + " (by: "
                + taskDue.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm")) + ")";
    }

    @Override
    public String getDate() {
        return taskDue.toString().substring(0, 10);
    }

    @Override
    public String toDataStringFormat() {
        return SYMBOL + SEPARATOR + (isDone ? 1 : 0) + SEPARATOR + description + SEPARATOR + taskDue + "\n";
    }
}
