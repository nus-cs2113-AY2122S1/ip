package commands;

import task.Deadline;
import java.time.LocalDateTime;

/**
 * Adds a deadline to the task list.
 */
public class AddDeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    public static final String MESSAGE_USAGE = "deadline: Adds a deadline to the task list.\n"
            + "\tParameters: DESCRIPTION d/DATE t/TIME\n"
            + "\tTIME parameter is optional. If TIME is not given, default time will be set to 12:00AM\n"
            + "\tExample: deadline homework d/2020-12-31 t/09:00\n";
    public static final String MESSAGE_SUCCESS = "New deadline added: %1$s";
    private final Deadline toAdd;

    /**
     * Constructor using raw values.
     * @param description
     * @param isDone
     * @param dateTime
     */
    public AddDeadlineCommand(String description, boolean isDone, LocalDateTime dateTime) {
        this.toAdd = new Deadline(description, isDone, dateTime);
    }

    @Override
    public CommandResult execute() {
        taskManager.addTask(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }
}