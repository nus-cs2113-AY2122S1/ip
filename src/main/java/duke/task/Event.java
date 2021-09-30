package duke.task;

import duke.task.Task;

public class Event extends Task {

    protected String at;

    public Event(String description, String at, boolean isDone) {
        super(description,isDone);
        this.at = at;
        this.taskType = 'E';
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + at + ")";
    }
}