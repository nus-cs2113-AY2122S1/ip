package duke.command;

public class InvalidCommand extends Command{

    private final String INVALID_COMMAND_MSG = "\n[Duke]:\n"
            + "=> Yikes, I do not recognise your input! (refer to 'help' command)";

    @Override
    public CommandResult executeCommand() {
        return new CommandResult(taskManager, INVALID_COMMAND_MSG, false, false);
    }
}
