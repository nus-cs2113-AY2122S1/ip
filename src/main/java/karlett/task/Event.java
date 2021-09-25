package karlett.task;

import karlett.storage.TaskListEncoder;

import java.io.IOException;

public class Event extends Task {

    public String getAt() {
        return at;
    }

    protected String at;

    /* constructor used for user input */
    public Event(String description, String at) throws IOException {
        this.description = description;
        this.isDone = false;
        this.at = at;
        increaseNumberOfTasks();
        printNewTaskAddedMessage();
        TaskListEncoder.appendNewEventToFile(this);
    }

    /* constructor used for loading file data */
    public Event(String description, String at, boolean isDone) throws IOException {
        this.description = description;
        this.isDone = isDone;
        this.at = at;
        increaseNumberOfTasks();
    }

    @Override
    public String toString() {
        return "[E] [" + this.getStatusIcon() + "] " + this.getDescription() + " (at: " + at + ")";
    }
}
