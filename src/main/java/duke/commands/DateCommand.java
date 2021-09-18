package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.tasks.TaskManager;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class DateCommand extends Command {

    private static final String DATE_MESSAGE =
            "Here are your tasks that are occurring on the date specified:";
    private static final String DATE_WRONG_FORMAT_ERROR =
            "OH NO! Please key in your date in the format yyyy-mm-dd...";

    private boolean isValidDate(String date) {
        try {
            LocalDate.parse(date);
        } catch (DateTimeParseException exception) {
            return false;
        }
        return true;
    }

    public DateCommand(String argument) {
        super(argument);
    }

    private String retrieveDateParameter(String argument) throws DukeException {
        if (!isValidDate(argument)) {
            throw new DukeException(DATE_WRONG_FORMAT_ERROR);
        }
        return argument;
    }

    @Override
    public CommandResult executeCommand() throws DukeException {
        String parameter = retrieveDateParameter(argument);
        ArrayList<Task> filteredList = TaskManager.filterList(parameter);
        String listOfTasks = TaskManager.listTasks(filteredList);
        CommandResult result = new CommandResult(DATE_MESSAGE + listOfTasks);
        return result;
    }
}
