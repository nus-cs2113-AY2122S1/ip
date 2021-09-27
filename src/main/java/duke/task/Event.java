package duke.task;

import java.time.LocalDate;

public class Event extends Task {

    /**
     * Date and time of event.
     */
    private LocalDate at;

    /**
     * Create a new Event task.
     *
     * @param description String description of the Deadline.
     * @param at          LocalDate object of the due date.
     */
    public Event(String description, LocalDate at) {
        super(description);
        super.setType("E");
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
