package commands;

/**
 * Clears the task list.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_USAGE = "clear: Clears the entire task list. \n"
            + "\tExample: clear\n";
    public static final String MESSAGE_SUCCESS = "History cleared.";

    @Override
    public CommandResult execute() {
        taskManager.clearHistory();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
