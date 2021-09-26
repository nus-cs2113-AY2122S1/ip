package duke.command;

import duke.task.TaskManager;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    /**
     * Represents the updated {@code TaskManager} object that was produced by the command.
     */
    private TaskManager taskManager;

    /**
     * Indicates whether the command executed will modify {@code taskManager}.
     */
    private boolean isModified;

    /**
     * Indicates whether the command executed is to terminate Duke's program.
     * Only the "bye" command will initialise {@code isExited} to be {@code true};
     */
    private boolean isExited;

    /**
     * The message to be displayed to the user after the command execution.
     * Contains description of the command execution result.
     */
    private String dukeMessage;

    /**
     * Constructor for command execution results that do not modify the {@code TaskManager} object.
     */
    public CommandResult(String dukeMessage, boolean isModified, boolean isExited) {
        this.isModified = isModified;
        this.isExited = isExited;
        this.dukeMessage = dukeMessage;
    }

    /**
     * Constructor for command execution results that modifies the {@code TaskManager} object.
     */
    public CommandResult(TaskManager taskManager, String dukeMessage, boolean isModified, boolean isExited) {
        this.taskManager = taskManager;
        this.isModified = isModified;
        this.isExited = isExited;
        this.dukeMessage = dukeMessage;
    }

    public TaskManager getTaskManager() {
        return this.taskManager;
    }

    public String getDukeMessage() {
        return this.dukeMessage;
    }

    public boolean getIsModified() {
        return this.isModified;
    }

    public boolean getIsExited() {
        return this.isExited;
    }


}
