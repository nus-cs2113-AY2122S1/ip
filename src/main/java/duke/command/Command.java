package duke.command;

import duke.task.TaskManager;

/**
 * Parent command class that will handle any valid command operations.
 */
public class Command {

    // Common command error messages
    final public static String TAG_NO_FORMAT = " <no additional input required>";
    final public static String TAG_TASK_NUMBER = " <task number(can be seen using the list command, eg. 1)>";
    final public static String TAG_TASK_DESCRIPTION = " <task description>";

    protected TaskManager taskManager;
    private int taskIndex = -1;
    protected boolean hasDataChange = false;

    public Command() {

    }

    public Command(int targetIndex) {
        this.taskIndex = targetIndex;
    }

    /**
     * Method to set the task manager for the command object to be performed on. Fatal error if the TaskManager object
     * is non-existent.
     *
     * @param taskManager A Task Manager object that is created during the setup phase of duke.
     * @throws CommandException Exception triggered if taskManager do not exist.
     */
    public void setTaskManager(TaskManager taskManager) throws CommandException {
        if (taskManager == null) {
            throw new CommandException("Error: task manager not found.");
        }
        this.taskManager = taskManager;
    }

    /**
     * Method to be handles by its children classes.
     *
     * @throws CommandException Exception to a command is a generic one and tries to execute.
     */
    public void execute() throws CommandException {
        throw new CommandException("Error: Command not found.");
    }

    public int getTaskIndex() {
        return taskIndex;
    }

    public boolean hasDataChange() {
        return hasDataChange;
    }

}
