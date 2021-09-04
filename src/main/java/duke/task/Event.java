package duke.task;

import duke.task.Task;

public class Event extends Task {

    /** Date and time of event. */
    private String at;

    public Event(String description, String at) {
        super(description);
        super.setType("E");
        this.at = at;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " (at: " + getAt() + ")";
    }

    public String getAt() {
        return at;
    }
}
