package duke.task;

import duke.task.Task;

public class Event extends Task {

    protected String type = "[E]";
    protected String at;

    public Event(String desc, String at) {
        super(desc);
        this.at = at;
    }

    @Override
    public String toString() {
        return type + super.toString() + " (at: " + at + ")";
    }
}
