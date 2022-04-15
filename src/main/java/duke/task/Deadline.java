package duke.task;

import duke.parser.Parser;

import java.time.LocalDateTime;

public class Deadline extends Task {
    private final LocalDateTime dateTime;

    public Deadline(String description, TaskType type, LocalDateTime dateTime) {
        super(description, type);
        this.dateTime = dateTime;
    }

    public Deadline(String description, TaskType type, boolean isDone, LocalDateTime dateTime) {
        super(description, type);
        this.isDone = isDone;
        this.dateTime = dateTime;
    }

    @Override
    public String getFileStringFormat() {
        return String.format("D | %s | %s", super.getFileStringFormat(), Parser.stringifyDateTimeForStorage(dateTime));
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (by: %s)", Parser.stringifyDateTimeForPrinting(dateTime));
    }
}
