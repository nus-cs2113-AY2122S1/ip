package duke.command;

import duke.common.Messages;
import duke.task.TaskManager;
import duke.task.TaskManagerException;

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
            throw new CommandException(Messages.ERROR_MESSAGE_INVALID_TASK_MANAGER);
        }
        this.taskManager = taskManager;
    }

    /**
     * Method to be handles by its children classes.
     *
     * @throws CommandException Exception to a command is a generic one and tries to execute.
     */
    public CommandResult execute() throws CommandException, TaskManagerException {
        throw new CommandException(Messages.ERROR_MESSAGE_UNKNOWN_COMMAND);
    }

    public int getTaskIndex() {
        return taskIndex;
    }

    public boolean hasDataChange() {
        return hasDataChange;
    }

    /**
     * Method called only when it is adding task related operation. This will display the task added and the current
     * total task now.
     *
     * @return The output response message after adding a task to be printed for user to see.
     */
    protected String getAddTaskResponseMessage() {
        int totalTask = taskManager.getTotalNumberOfTasks();
        String result = String.format(Messages.MESSAGE_ADD_TASK, taskManager.getTaskInfo(totalTask - 1));
        result += "\n";
        result += String.format(Messages.MESSAGE_TOTAL_TASK_NOW, totalTask);
        return result;
    }

}
