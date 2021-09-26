package duke.command;

import duke.exception.ToDoFormatException;
import duke.task.TaskManager;

/**
 * Represents an add {@code ToDo} task command.
 * This command adds a new {@code ToDo} task into the user's existing tasklist.
 */
public class AddToDoCommand extends Command {

    public AddToDoCommand(TaskManager taskManager, String commandArguments) {
        super(taskManager, commandArguments);
    }

    /**
     * Executes the command to add a {@code ToDo} task into the user's tasklist.
     * Stores the message to be displayed to the user into {@code dukeMessage}
     * and to be passed as an input parameter of the {@code CommandResult} object to be returned.
     *
     * @return the command result of the execution.
     */
    @Override
    public CommandResult executeCommand() {

        String dukeMessage = "";

        try {
            dukeMessage = taskManager.addToDo(commandArguments);
        } catch (ToDoFormatException e) {
            dukeMessage = e.toString();
        }

        return new CommandResult(taskManager, dukeMessage, true, false);
    }

}
