package duke.task;

import java.time.LocalDate;

public class Event extends Task {

    private final String TASK_TYPE_EVENT = "E";

    /**
     * Date and time of event.
     */
    private LocalDate at;

    /**
     * Creates a new Event task.
     *
     * @param description String description of the Deadline.
     * @param at          LocalDate object of the due date.
     */
    public Event(String description, LocalDate at) {
        super(description);
        super.setType(TASK_TYPE_EVENT);
        this.at = at;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " (at: " + getAt() + ")";
    }

    public LocalDate getAt() {
        return at;
    }
}
