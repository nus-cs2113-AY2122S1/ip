package duke.commands;

import duke.exceptions.DukeException;
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

    private String[] retrieveEventParameters(String argument) throws DukeException {

        String[] parameters = PARSER.separateEvent(argument);
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
        taskManager.addTask(event);
        CommandResult result = new CommandResult(
                ADD_TASK_MESSAGE + "\n" + event.toString() + "\n"
                        + "You have " + TaskManager.getCurrentTasksCount() + " tasks in your list now!");
        return result;
    }
}
