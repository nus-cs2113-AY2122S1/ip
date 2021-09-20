package duke.task;

import duke.parser.Parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    protected String description;
    protected boolean isDone;
    protected boolean hasDateTime;
    protected LocalDateTime dateTime;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.hasDateTime = false;
    }

    protected void setDateAndTime(String line) {
        dateTime = Parser.parseDateAndTime(line);
        if (dateTime != null) {
            hasDateTime = true;
        }
    }

    protected String getDateAndTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return dateTime.format(formatter);
    }

    // Setter methods
    public void markAsDone() {
        this.isDone = true;
    }

    // Getter methods
    public String getDescription() {
        return description;
    }

    // Mark done task with X
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public boolean getIsDone() {
        return isDone;
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.getDescription();
    }
}
