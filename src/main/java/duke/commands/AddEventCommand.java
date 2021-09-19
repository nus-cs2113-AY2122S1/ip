package duke.commands;

import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.tasks.Event;
import duke.tasks.TaskManager;

public class AddEventCommand extends Command {

    private static final String ADD_TASK_MESSAGE = "Yay! I have added the following task for you:";
    private static final String EVENT_DESCRIPTION_ERROR =
            "OH NO! You need to provide a description for your event...";
    private static final String EVENT_DATE_ERROR =
            "OH NO! You need to specify a date and time for your event...";
    private static final String ILLEGAL_CHAR = "|";
    private static final String ILLEGAL_CHAR_ERROR = "Please do not use \"|\" in your input...";

    private Event event;

    public AddEventCommand(String argument) throws DukeException {
        super(argument);
        if (argument.contains(ILLEGAL_CHAR)) {
            throw new DukeException(ILLEGAL_CHAR_ERROR);
        }
    }

    /**
     * Gets the description and date and time from the argument provided by the user.
     *
     * @param argument Argument provided by the user after separating the command word from the user input string
     * @return <code>String</code> array of size 2 where first entry is the description and the second entry is
     * the date and time
     * @throws DukeException If either entry of the return array is empty
     */

    private String[] retrieveEventParameters(String argument) throws DukeException {

        String[] parameters = Parser.separateEvent(argument);
        String description = parameters[0];
        String dateAndTime = parameters[1];

        if (isEmptyArgument(description)) {
            throw new DukeException(EVENT_DESCRIPTION_ERROR);
        }
        if (isEmptyArgument(dateAndTime)) {
            throw new DukeException(EVENT_DATE_ERROR);
        }

        return parameters;
    }

    @Override
    public CommandResult executeCommand() throws DukeException {
        String[] parameters = retrieveEventParameters(argument);
        event = new Event(parameters[0], parameters[1]);
        TaskManager.addTask(event);
        CommandResult result = new CommandResult(
                ADD_TASK_MESSAGE + "\n" + event.toString() + "\n"
                        + "You have " + TaskManager.getCurrentTasksCount() + " tasks in your list now!");
        return result;
    }
}
