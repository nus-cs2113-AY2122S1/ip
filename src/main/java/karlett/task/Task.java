package karlett.task;

import karlett.storage.TaskListEncoder;
import karlett.tasklist.TaskList;
import karlett.ui.TextUi;

import java.io.IOException;

public class Task {

    protected String description;
    protected boolean isDone;
    private TaskList tasks;

    /* constructor used for user input */
    public Task(String description) throws IOException {
        this.description = description;
        this.isDone = false;
    }

    /* constructor used for loading file data */
    public Task(String description, boolean isDone) throws IOException {
        this.description = description;
        this.isDone = isDone;
    }

    public Task() {
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "V" : " ");  // mark done task with V
    }

    public void markAsDone(int index) throws IOException {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[T] [" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
