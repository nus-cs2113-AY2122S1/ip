package duke.task;

import duke.task.Task;

public class Deadline extends Task {

    protected String type = "[D]";
    protected String by;

    public Deadline(String desc, String by) {
        super(desc);
        this.by = by;
    }

    @Override
    public String toStorageString() {
        return type + " | " + super.toStorageString() + " | " + by;
    }

    @Override
    public String toString() {
        return type + super.toString() + " (by: " + by + ")";
    }
}
