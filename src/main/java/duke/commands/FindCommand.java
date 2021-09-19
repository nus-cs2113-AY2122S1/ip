package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.tasks.TaskManager;
import java.util.ArrayList;

public class FindCommand extends Command {

    private static final String FIND_MESSAGE =
            "Here are the matching tasks in your list:";
    private static final String FIND_ERROR =
            "OH NO! Please provide a keyword to find your tasks...";

    public FindCommand(String argument) {
        super(argument);
    }

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
