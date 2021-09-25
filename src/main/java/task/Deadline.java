package task;

import java.time.LocalDateTime;

public class Deadline extends TaskWithDate {

    public Deadline(String description, boolean isDone, LocalDateTime dateTime) {
        super(description, isDone, dateTime);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString();
    }

    @Override
    public String toFileString() {
        return "D" + super.toFileString();
    }

}