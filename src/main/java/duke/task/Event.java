package duke.task;

import java.time.LocalDate;

/**
 * Represents an Event task and has a type of D. It is defined by a description String and a date String.
 */
public class Event extends Deadline {
    public Event(String description, LocalDate date) {
        super(description, date);
        this.type = 'E';
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
