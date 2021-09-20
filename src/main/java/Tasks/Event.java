package Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task
 */
public class Event extends Task{
    protected LocalDateTime timing;

    public Event(String task, boolean isDone, LocalDateTime timing) {
        super(task, isDone, TaskTypes.EVENT);
        this.timing = timing;
    }

    @Override
    public String toString() {
        return super.toString() + " | at: " + timing.format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss"));
    }
}
