package duke.command;

import duke.exception.DoneFormatException;
import duke.exception.InvalidTaskIdException;
import duke.exception.TaskAlreadyDoneException;
import duke.task.TaskManager;

/**
 * Represents a command to mark a {@code Task} as done.
 * This command marks an existing task in the user's tasklist that is incomplete to done.
 */
public class SetTaskDoneCommand extends Command {

    public SetTaskDoneCommand(TaskManager taskManager, String commandArguments) {
        super(taskManager, commandArguments);
    }

    /**
     * Executes the command to mark a {@code Task} in the user's tasklist as done.
     * Stores the message to be displayed to the user into {@code dukeMessage}
     * and to be passed as an input parameter of the {@code CommandResult} object to be returned.
     *
     * @return the command result of the execution.
     */
    @Override
    public CommandResult executeCommand() {

        String dukeMessage = "";

        try {
            dukeMessage = taskManager.setTaskComplete(commandArguments);
        } catch (DoneFormatException e) {
            dukeMessage = e.toString();
        } catch (InvalidTaskIdException e) {
            dukeMessage = e.toString();
        } catch (TaskAlreadyDoneException e) {
            dukeMessage = e.toString();
        }

        return new CommandResult(taskManager, dukeMessage, true, false);
    }

}
