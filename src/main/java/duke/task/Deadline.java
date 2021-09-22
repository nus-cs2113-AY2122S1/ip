package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime deadline;

    public Deadline(String description, LocalDateTime deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = deadline;
    }

    public String getDate() {
        return this.deadline.format(dateTimeFormat);
    }

    public String toString() {
        return String.format("[D][%s] %s (by: %s)", isDone ? "X" : " ", this.taskDescription,
                this.deadline.format(dateTimeFormat));
    }
}
