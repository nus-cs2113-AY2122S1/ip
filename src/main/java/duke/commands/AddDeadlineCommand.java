package duke.commands;

import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.tasks.Deadline;
import duke.tasks.TaskManager;

/** Includes the operations needed to add a <code>Deadline</code> to the task list. */
public class AddDeadlineCommand extends Command {

    private static final String ADD_TASK_MESSAGE = "Yay! I have added the following task for you:";
    private static final String DEADLINE_DESCRIPTION_ERROR =
            "OH NO! You need to provide a description for your deadline task...";
    private static final String DEADLINE_DATE_ERROR =
            "OH NO! You need to specify a due date for your deadline task...";
    private static final String ILLEGAL_CHAR = "|";
    private static final String ILLEGAL_CHAR_ERROR = "Please do not use \"|\" in your input...";

    private Deadline deadline;

    /**
     * Constructed when the command word of the user input is "deadline".
     *
     * @param argument Argument provided by the user after separating the command word from the user input string
     * @throws DukeException If the argument contains illegal characters that will potentially corrupt the data
     * stored in the storage.
     */
    public AddDeadlineCommand(String argument) throws DukeException {
        super(argument);
        if (argument.contains(ILLEGAL_CHAR)) {
            throw new DukeException(ILLEGAL_CHAR_ERROR);
        }
    }

    /**
     * Gets the description and deadline from the argument provided by the user.
     *
     * @param argument Argument provided by the user after separating the command word from the user input string
     * @return <code>String</code> array of size 2 where first entry is the description and the second entry is
     * the deadline
     * @throws DukeException If either entry of the return array is empty
     */
    private String[] retrieveDeadlineParameters(String argument) throws DukeException {

        String[] parameters = Parser.separateDeadline(argument);
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
        TaskManager.addTask(deadline);
        CommandResult result = new CommandResult(
                ADD_TASK_MESSAGE + "\n" + deadline.toString() + "\n"
                        + "You have " + TaskManager.getCurrentTasksCount() + " tasks in your list now!");
        return result;
    }
}
