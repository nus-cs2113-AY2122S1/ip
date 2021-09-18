package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.TaskManager;

public class AddDeadlineCommand extends Command {

    private static final String ADD_TASK_MESSAGE = "Yay! I have added the following task for you:";
    private static final String DEADLINE_DESCRIPTION_ERROR =
            "OH NO! You need to provide a description for your deadline task...";
    private static final String DEADLINE_DATE_ERROR =
            "OH NO! You need to specify a due date for your deadline task...";
    private static final String ILLEGAL_CHAR = "|";
    private static final String ILLEGAL_CHAR_ERROR = "Please do not use \"|\" in your input...";

    private Deadline deadline;

    public AddDeadlineCommand(String argument) throws DukeException {
        super(argument);
        if (argument.contains(ILLEGAL_CHAR)) {
            throw new DukeException(ILLEGAL_CHAR_ERROR);
        }
    }

    private String[] retrieveDeadlineParameters(String argument) throws DukeException {

        String[] parameters = PARSER.separateDeadline(argument);
        String description = parameters[0];
        String taskDue = parameters[1];

        if (isEmptyArgument(description)) {
            throw new DukeException(DEADLINE_DESCRIPTION_ERROR);
        }
        if (isEmptyArgument(taskDue)) {
            throw new DukeException(DEADLINE_DATE_ERROR);
        }

        return parameters;
    }

    @Override
    public CommandResult executeCommand() throws DukeException {
        String[] parameters = retrieveDeadlineParameters(argument);
        deadline = new Deadline(parameters[0], parameters[1]);
        taskManager.addTask(deadline);
        CommandResult result = new CommandResult(
                ADD_TASK_MESSAGE + "\n" + deadline.toString() + "\n"
                        + "You have " + TaskManager.getCurrentTasksCount() + " tasks in your list now!");
        return result;
    }
}
