package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.TaskManager;
import duke.tasks.ToDo;

public class AddToDoCommand extends Command {

    private static final String ADD_TASK_MESSAGE = "Yay! I have added the following task for you:";
    private static final String TODO_ERROR =
            "OH NO! You need to provide a description for your todo...";
    private static final String ILLEGAL_CHAR = "|";
    private static final String ILLEGAL_CHAR_ERROR = "Please do not use \"|\" in your input...";

    private ToDo toDo;

    public AddToDoCommand(String argument) throws DukeException {
        super(argument);
        if (argument.contains(ILLEGAL_CHAR)) {
            throw new DukeException(ILLEGAL_CHAR_ERROR);
        }
    }

    private String retrieveTodoParameter(String argument) throws DukeException {
        if (isEmptyArgument(argument)) {
            throw new DukeException(TODO_ERROR);
        }
        return argument;
    }

    private ToDo createToDo(String description) {
        return new ToDo(description);
    }

    @Override
    public CommandResult executeCommand() throws DukeException {
        String parameter = retrieveTodoParameter(argument);
        toDo = createToDo(parameter);
        taskManager.addTask(toDo);
        CommandResult result = new CommandResult(
                ADD_TASK_MESSAGE + "\n" + toDo.toString() + "\n"
                        + "You have " + TaskManager.getCurrentTasksCount() + " tasks in your list now!");
        return result;
    }
}
