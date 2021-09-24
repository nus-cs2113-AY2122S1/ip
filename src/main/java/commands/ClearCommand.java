package commands;

public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Clears the entire task list. \n"
            + "Example: " + COMMAND_WORD;
    public static final String MESSAGE_CLEAR_SUCCESS = "History cleared.";

    @Override
    public CommandResult execute() {
        taskManager.clearHistory();
        return new CommandResult(MESSAGE_CLEAR_SUCCESS);
    }
}
