package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDate dueTime;

    public Deadline(String description, String dueTime) {
        super(description);
        try {
            this.dueTime = LocalDate.parse(dueTime);
        } catch (DateTimeParseException e) {
            throw e;
        }
        this.type = 'D';
    }

    public Deadline(String name, String dueTime, boolean isDone) {
        super(name, isDone);
        try {
            this.dueTime = LocalDate.parse(dueTime);
        } catch (DateTimeParseException e) {
            throw e;
        }
        this.type = 'D';
    }

    @Override
    public String toString() {
        String done = isDone ? "X" : " " ;
        return "[" + type + "]" + "[" + done + "] " + description + " (by: " + dueTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    @Override
    public String formatData() {
        return super.formatData() + "|" + dueTime;
    }
}
