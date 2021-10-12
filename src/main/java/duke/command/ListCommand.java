package duke.command;

import duke.exception.EmptyTasklistException;
import duke.task.TaskManager;

/**
 * Represents a command to list all {@code Task} in a tasklist.
 * The command prints all current tasks in the user's tasklist.
 */
public class ListCommand extends Command{

    public ListCommand(TaskManager taskManager) {
        super(taskManager);
    }

    /**
     * Executes the command to show all current tasks in the user's tasklist.
     * Stores the message to be displayed to the user into {@code dukeMessage}
     * and to be passed as an input parameter of the {@code CommandResult} object to be returned.
     *
     * @return the command result of the execution.
     */
    @Override
    public CommandResult executeCommand() {

        String dukeMessage = "";

        try {
            dukeMessage = taskManager.getTasklistEntries();
        } catch (EmptyTasklistException e) {
            dukeMessage = e.toString();
        }

        return new CommandResult(taskManager, dukeMessage, false, false);
    }

}
