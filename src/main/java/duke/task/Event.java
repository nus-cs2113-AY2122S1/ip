package duke.task;

import java.time.LocalDate;

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
