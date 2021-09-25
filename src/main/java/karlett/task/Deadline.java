package karlett.task;

import karlett.storage.TaskListEncoder;
import karlett.tasklist.TaskList;
import karlett.ui.TextUi;

import java.io.IOException;

public class Deadline extends Task {

    public String getBy() {
        return by;
    }

    protected String by;

    /* constructor used for user input */
    public Deadline(String description, String by) throws IOException {
        this.description = description;
        this.isDone = false;
        this.by = by;
        TaskList.increaseNumberOfTasks();
        TextUi.printNewTaskAddedMessage(this);
        TaskListEncoder.appendNewDeadlineToFile(this);
    }

    /* constructor used for loading file data */
    public Deadline(String description, String by, boolean isDone) throws IOException {
        this.description = description;
        this.isDone = isDone;
        this.by = by;
        TaskList.increaseNumberOfTasks();
    }

    @Override
    public String toString() {
        return "[D] [" + this.getStatusIcon() + "] " + this.getDescription() + " (by: " + by + ")";
    }
}
