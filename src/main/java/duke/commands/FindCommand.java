package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.tasks.TaskManager;
import java.util.ArrayList;

/** Includes the operations needed to find a task with a specific keyword in its description */
public class FindCommand extends Command {

    private static final String FIND_MESSAGE =
            "Here are the matching tasks in your list:";
    private static final String FIND_ERROR =
            "OH NO! Please provide a keyword to find your tasks...";

    /**
     * Constructed when the command word in the user input is "find".
     *
     * @param argument Argument provided by the user after separating the command word from the user input string
     */
    public FindCommand(String argument) {
        super(argument);
    }

    /**
     * Gets the keyword from the argument provided by the user in the user input.
     *
     * @param argument Argument provided by the user after separating the command word from the user input string
     * @return Keyword to search for
     * @throws DukeException If the given argument by the user is empty
     */
    private String retrieveFindParameter(String argument) throws DukeException {
        if (isEmptyArgument(argument)) {
            throw new DukeException(FIND_ERROR);
        }
        return argument;
    }

    @Override
    public CommandResult executeCommand() throws DukeException {
        String parameter = retrieveFindParameter(argument);
        ArrayList<Task> filteredList = TaskManager.filterListByKeyword(parameter);
        String listOfTasks = TaskManager.listTasks(filteredList);
        CommandResult result = new CommandResult(FIND_MESSAGE + listOfTasks);
        return result;
    }
}
