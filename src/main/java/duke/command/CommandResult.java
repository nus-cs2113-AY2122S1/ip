package duke.command;

import duke.task.TaskManager;

public class CommandResult {

    public TaskManager taskManager;
    private boolean isModified;
    private boolean isExited;
    private String dukeMessage;

    public CommandResult(String dukeMessage, boolean isModified, boolean isExited) {
        this.isModified = isModified;
        this.isExited = isExited;
        this.dukeMessage = dukeMessage;
    }

    public CommandResult(TaskManager taskManager, String dukeMessage, boolean isModified, boolean isExited) {
        this.taskManager = taskManager;
        this.isModified = isModified;
        this.isExited = isExited;
        this.dukeMessage = dukeMessage;
    }

    public TaskManager getTaskManager() {
        return this.taskManager;
    }

    public boolean getIsModified() {
        return this.isModified;
    }



}
