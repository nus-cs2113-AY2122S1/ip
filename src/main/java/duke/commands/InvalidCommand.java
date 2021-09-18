package duke.commands;

public class InvalidCommand extends Command {

    private static final String INVALID_COMMAND_MESSAGE =
            "Sorry... I did not understand that, can you try again?\n"
                    + "Or you can enter \"help\" to see what I can do for you!";

    public InvalidCommand() {
        super();
    }

    @Override
    public CommandResult executeCommand() {
        CommandResult result = new CommandResult(INVALID_COMMAND_MESSAGE);
        return result;
    }
}
