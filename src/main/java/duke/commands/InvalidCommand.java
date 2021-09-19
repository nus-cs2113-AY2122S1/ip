package duke.commands;

/** Includes the operations needed to resolve an invalid command from the user. */
public class InvalidCommand extends Command {

    private static final String INVALID_COMMAND_MESSAGE =
            "Sorry... I did not understand that, can you try again?\n"
                    + "Or you can enter \"help\" to see what I can do for you!";

    /** Constructed when the command word of the user input does not match any existing commands in
     * <code>Duke</code>. */
    public InvalidCommand() {
        super();
    }

    @Override
    public CommandResult executeCommand() {
        CommandResult result = new CommandResult(INVALID_COMMAND_MESSAGE);
        return result;
    }
}
