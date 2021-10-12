package duke.command;

import duke.exception.DeleteFormatException;
import duke.exception.InvalidTaskIdException;
import duke.task.TaskManager;

/**
 * Represents a command to delete a {@code Task}.
 * The command deletes an existing task from the user's tasklist.
 */
public class DeleteTaskCommand extends Command {

    public DeleteTaskCommand(TaskManager taskManager, String commandArguments) {
        super(taskManager, commandArguments);
    }

    /**
     * Executes the command to delete a task from the user's tasklist.
     * Stores the message to be displayed to the user into {@code dukeMessage}
     * and to be passed as an input parameter of the {@code CommandResult} object to be returned.
     *
     * @return the command result of the execution.
     */
    @Override
    public CommandResult executeCommand() {

        String dukeMessage = "";

        try {
            dukeMessage = taskManager.deleteTask(commandArguments);
        } catch (DeleteFormatException e) {
            dukeMessage = e.toString();
        } catch (InvalidTaskIdException e) {
            dukeMessage = e.toString();
        }

        return new CommandResult(taskManager, dukeMessage, true, false);
    }
}
