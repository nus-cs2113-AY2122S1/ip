package commands;

/**
 * Exits the program.
 */
public class ExitCommand extends Command{
    public static final String COMMAND_WORD = "bye";
    public static final String MESSAGE_USAGE = "bye: Exits the program.\n"
            + "\tExample: bye\n";
    private static final String MESSAGE_EXIT_SUCCESS = "finally. bye.";

    @Override
    public CommandResult execute() {
        return new CommandResult(MESSAGE_EXIT_SUCCESS);
    }

    /**
     * Determines if the command is an ExitCommand.
     * @param command type of command
     * @return true if the command is an ExitCommand
     */
    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }
}
