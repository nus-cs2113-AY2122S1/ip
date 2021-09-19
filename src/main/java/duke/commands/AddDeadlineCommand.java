package duke.commands;

import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.tasks.Deadline;
import duke.tasks.TaskManager;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class AddDeadlineCommand extends Command {

    private static final String ADD_TASK_MESSAGE = "Yay! I have added the following task for you:";
    private static final String DEADLINE_DESCRIPTION_ERROR =
            "OH NO! You need to provide a description for your deadline task...";
    private static final String DEADLINE_DATE_ERROR =
            "OH NO! You need to specify a due date for your deadline task...";
    private static final String DATE_WRONG_FORMAT_ERROR =
            "OH NO! Please key in your date in the format [yyyy-mm-dd]T[hh:mm]...";
    private static final String ILLEGAL_CHAR = "|";
    private static final String ILLEGAL_CHAR_ERROR = "Please do not use \"|\" in your input...";

    private Deadline deadline;

    public AddDeadlineCommand(String argument) throws DukeException {
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
        if (!isValidDateTime(taskDue)) {
            throw new DukeException(DATE_WRONG_FORMAT_ERROR);
        }

        return parameters;
    }

    @Override
    public CommandResult executeCommand() throws DukeException {

        String[] parameters = retrieveDeadlineParameters(argument);
        deadline = new Deadline(parameters[0], LocalDateTime.parse(parameters[1]));
        TaskManager.addTask(deadline);
        CommandResult result = new CommandResult(
                ADD_TASK_MESSAGE + "\n" + deadline.toString() + "\n"
                        + "You have " + TaskManager.getCurrentTasksCount() + " tasks in your list now!");
        return result;
    }
}
