package duke.task;

import java.time.LocalDate;

public class Event extends Task {

    /**
     * Date and time of event.
     */
    private LocalDate at;

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
