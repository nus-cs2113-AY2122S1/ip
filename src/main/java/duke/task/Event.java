package duke.task;

import duke.task.Task;

public class Event extends Task {
    protected String time;
    public Event(String content, String time) {
        super(content);
        this.time = time;
    }
    @Override
    public String toString() {
        return "[E]" + "[" + this.TaskStatus() + "] " + this.content
                + "(at: " + this.time + ")";
    }

    public String getTime() {
        return this.time;
    }
}