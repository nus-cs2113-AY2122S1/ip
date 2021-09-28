package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.tasks.TaskManager;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/** Includes the operations needed to find a task with a specific date attached. */
public class DateCommand extends Command {

    private static final String DATE_MESSAGE =
            "Here are your tasks that are occurring on the date specified:";
    private static final String DATE_WRONG_FORMAT_ERROR =
            "OH NO! Please key in your date in the format yyyy-mm-dd...";

    /**
     * <p>Checks if the date and time string is of a valid format.</p>
     * <p>Valid form: [yyyy-mm-dd]T[HH:MM]</p>
     *
     * @param date date and time to check for validity
     * @return
     * <p>{@code true} - if the specified date is valid</p>
     * <p>{@code false} - otherwise</p>
     */
    private boolean isValidDate(String date) {
        try {
            LocalDate.parse(date);
        } catch (DateTimeParseException exception) {
            return false;
        }
        return true;
    }

    /**
     * Constructed when the command word of the user input is "date".
     *
     * @param argument Argument provided by the user after separating the command word from the user input string
     */
    public DateCommand(String argument) {
        super(argument);
    }

    /**
     * Checks if the argument is a date that is of the valid form.
     *
     * @param argument Argument provided by the user after separating the command word from the user input string
     * @return Valid date in string format
     * @throws DukeException If the argument provided is not of the valid format
     */
    private String retrieveDateParameter(String argument) throws DukeException {
        if (!isValidDate(argument)) {
            throw new DukeException(DATE_WRONG_FORMAT_ERROR);
        }
        return argument;
    }

    @Override
    public CommandResult executeCommand() throws DukeException {
        String parameter = retrieveDateParameter(argument);
        ArrayList<Task> filteredList = TaskManager.filterListByDate(parameter);
        String listOfTasks = TaskManager.listTasks(filteredList);
        CommandResult result = new CommandResult(DATE_MESSAGE + listOfTasks);
        return result;
    }
}
