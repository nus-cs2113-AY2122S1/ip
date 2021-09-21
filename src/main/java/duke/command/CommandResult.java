package duke.command;

import duke.task.TaskManager;

public class CommandResult {

    public TaskManager taskManager;
    boolean isModified;
    boolean isExited;

    public CommandResult(TaskManager taskManager, boolean isModified, boolean isExited) {
        this.taskManager = taskManager;
        this.isModified = isModified;
        this.isExited = isExited;
    }

    public TaskManager getTaskManager() {
        return this.taskManager;
    }

    public boolean getIsModified() {
        return this.isModified;
    }



}
