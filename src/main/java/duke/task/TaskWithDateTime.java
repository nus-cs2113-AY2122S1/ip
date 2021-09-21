package duke.task;

import java.time.LocalDateTime;

/**
 * Tasks that contain additional date and time information.
 */
public abstract class TaskWithDateTime extends Task {
    protected LocalDateTime dateTime;

    public TaskWithDateTime(String name, LocalDateTime dateTime) {
        super(name);
        this.dateTime = dateTime;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
