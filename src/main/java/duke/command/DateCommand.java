package duke.command;

import duke.common.CommonFormat;
import duke.common.Messages;
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
    public CommandResult execute() {
        String stringDate = date.format(CommonFormat.formatterDateOnly);
        String result = String.format(Messages.MESSAGE_COMMAND_DATE + "\n", stringDate);
        result += taskManager.getTaskOnDate(stringDate);
        return new CommandResult(result);
    }
}
