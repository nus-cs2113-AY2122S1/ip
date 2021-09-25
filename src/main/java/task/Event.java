package task;

import java.time.LocalDateTime;

public class Event extends TaskWithDate {

    public Event(String description, boolean isDone, LocalDateTime dateTime) {
        super(description, isDone, dateTime);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString();
    }

    @Override
    public String toFileString() {
        return "E" + super.toFileString();
    }

}