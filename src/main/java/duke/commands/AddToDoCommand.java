package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.TaskManager;
import duke.tasks.ToDo;

/** Includes the operations needed to add a {@code ToDo} to the task list. */
public class AddToDoCommand extends Command {

    private static final String ADD_TASK_MESSAGE = "Yay! I have added the following task for you:";
    private static final String TODO_ERROR =
            "OH NO! You need to provide a description for your todo...";
    private static final String ILLEGAL_CHAR = "|";
    private static final String ILLEGAL_CHAR_ERROR = "Please do not use \"|\" in your input...";

    private ToDo toDo;

    /**
     * Constructed when the command word of the user input is "todo".
     *
     * @param argument Argument provided by the user after separating the command word from the user input string
     * @throws DukeException If the argument contains illegal characters that will potentially corrupt the data
     * stored in the storage.
     */
    public AddToDoCommand(String argument) throws DukeException {
        super(argument);
        if (argument.contains(ILLEGAL_CHAR)) {
            throw new DukeException(ILLEGAL_CHAR_ERROR);
        }
    }

    /**
     * Checks if the argument provided by the user is empty.
     *
     * @param argument Argument provided by the user after separating the command word from the user input string
     * @return Description of the {@code ToDo}.
     * @throws DukeException If the argument specified is empty
     */
    private String retrieveTodoParameter(String argument) throws DukeException {
        if (isEmptyArgument(argument)) {
            throw new DukeException(TODO_ERROR);
        }
        return argument;
    }

    @Override
    public CommandResult executeCommand() throws DukeException {
        String parameter = retrieveTodoParameter(argument);
        toDo = new ToDo(parameter);
        TaskManager.addTask(toDo);
        CommandResult result = new CommandResult(
                ADD_TASK_MESSAGE + "\n" + toDo.toString() + "\n"
                        + "You have " + TaskManager.getCurrentTasksCount() + " tasks in your list now!");
        return result;
    }
}
