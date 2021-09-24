package duke.command;

import duke.common.CommonFormat;
import java.time.LocalDate;

/**
 * Date command that will find any task that happen on the specified date.
 */
public class DateCommand extends Command {

    final public static String COMMAND_WORD = "date";

    final public static String MESSAGE_FORMAT = COMMAND_WORD + " " + CommonFormat.FORMAT_DATE;

    private LocalDate date;

    public DateCommand(LocalDate date) {
        this.date = date;
    }

    /**
     * Method to execute the command by calling the TaskManager to perform its specified operation.
     */
    @Override
    public void execute() {
        taskManager.printTaskOnDate(date.format(CommonFormat.formatterDateOnly));
    }
}
