package duke.task;

import java.time.LocalDateTime;

public abstract class TaskWithDateTime extends Task {

    public TaskWithDateTime(String name) {
        super(name);
    }

    public abstract LocalDateTime getDateTime();
}
