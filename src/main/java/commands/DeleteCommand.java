package commands;

import error.Error;
import task.TaskManager;

/**
 * Removes a task from TaskManager.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    /**
     * Creates a new delete command and set the TaskManager object to perform operations on.
     *
     * @param taskManager The TaskManager object to execute commands on.
     */
    public DeleteCommand(TaskManager taskManager) {
        super(taskManager);
    }

    /**
     * Removes a task.
     *
     * @return The delete command type.
     */
    @Override
    public String executeCommand() {
        try {
            taskManager.deleteTask(commandComponents);
        } catch (IndexOutOfBoundsException e) {
            Error.displayTaskNonExistentError();
        } catch (NumberFormatException e) {
            Error.displayNotANumberError();
        }
        return COMMAND_WORD;
    }
}
