package duke.commands;

import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.tasks.Event;
import duke.tasks.TaskManager;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class AddEventCommand extends Command {

    private static final String ADD_TASK_MESSAGE = "Yay! I have added the following task for you:";
    private static final String EVENT_DESCRIPTION_ERROR =
            "OH NO! You need to provide a description for your event...";
    private static final String EVENT_DATE_ERROR =
            "OH NO! You need to specify a date and time for your event...";
    private static final String DATE_WRONG_FORMAT_ERROR =
            "OH NO! Please key in your date in the format [yyyy-mm-dd]T[hh:mm]...";
    private static final String ILLEGAL_CHAR = "|";
    private static final String ILLEGAL_CHAR_ERROR = "Please do not use \"|\" in your input...";

    private Event event;

    public AddEventCommand(String argument) throws DukeException {
        super(argument);
        if (argument.contains(ILLEGAL_CHAR)) {
            throw new DukeException(ILLEGAL_CHAR_ERROR);
        }
    }

    private boolean isValidDateTime(String date) {
        try {
            LocalDateTime.parse(date);
        } catch (DateTimeParseException exception) {
            return false;
        }
        return true;
    }

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
        if (!isValidDateTime(dateAndTime)) {
            throw new DukeException(DATE_WRONG_FORMAT_ERROR);
        }

        return parameters;
    }

    @Override
    public CommandResult executeCommand() throws DukeException {

        String[] parameters = retrieveEventParameters(argument);
        event = new Event(parameters[0], LocalDateTime.parse(parameters[1]));
        TaskManager.addTask(event);
        CommandResult result = new CommandResult(
                ADD_TASK_MESSAGE + "\n" + event.toString() + "\n"
                        + "You have " + TaskManager.getCurrentTasksCount() + " tasks in your list now!");
        return result;
    }
}
