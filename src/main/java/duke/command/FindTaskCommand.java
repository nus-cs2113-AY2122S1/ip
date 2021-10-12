package duke.command;

import duke.exception.NoTaskFoundException;
import duke.task.TaskManager;

/**
 * Represents a command to find a {@code Task} in the user's tasklist.
 * The command searches all tasks in the user's tasklist that contains a given keyword in their task description.
 */
public class FindTaskCommand extends Command {

    public FindTaskCommand (TaskManager taskManager, String commandArguments) {
        super(taskManager, commandArguments);
    }

    /**
     * Executes the command to search through all tasks in the user's tasklist that contains the search term.
     * Stores the message to be displayed to the user into {@code dukeMessage}
     * and to be passed as an input parameter of the {@code CommandResult} object to be returned.
     *
     * @return the command result of the execution.
     */
    @Override
    public CommandResult executeCommand() {

        String dukeMessage = "";

        try {
            dukeMessage = taskManager.findTask(commandArguments);
        } catch (NoTaskFoundException e) {
            dukeMessage = e.toString();
        }

        return new CommandResult(taskManager, dukeMessage, false, false);
    }

}
