package duke.task;

import duke.Parser;

import java.time.LocalDateTime;

public class Event extends Task {
    private final LocalDateTime dateTime;

    public Event(String description, TaskType type, LocalDateTime dateTime) {
        super(description, type);
        this.type = TaskType.EVENT;
        this.dateTime = dateTime;
    }

    public Event(String description, TaskType type, boolean isDone, LocalDateTime dateTime) {
        super(description, type);
        this.type = TaskType.EVENT;
        this.isDone = isDone;
        this.dateTime = dateTime;
    }

    @Override
    public String getFileStringFormat() {
        return String.format("E | %s | %s", super.getFileStringFormat(), Parser.stringifyDateTimeForStorage(dateTime));
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (at: %s)", Parser.stringifyDateTimeForPrinting(dateTime));
    }
}
