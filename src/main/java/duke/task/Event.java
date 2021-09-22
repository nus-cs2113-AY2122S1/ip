package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime date;

    public Event(String description, LocalDateTime date, boolean isDone) {
        super(description, isDone);
        this.date = date;
    }

    public String getDate() {
        return this.date.format(dateTimeFormat);
    }

    public String toString() {
        return String.format("[E][%s] %s (at: %s)", isDone ? "X" : " ", this.taskDescription,
                this.date.format(dateTimeFormat));
    }
}
