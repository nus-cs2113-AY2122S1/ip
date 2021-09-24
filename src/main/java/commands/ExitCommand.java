package commands;

public class ExitCommand extends Command{
    public static final String COMMAND_WORD = "bye";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exits the program.";
    public static final String MESSAGE_EXIT_SUCCESS = "finally. bye.";

    @Override
    public CommandResult execute() {
        return new CommandResult(MESSAGE_EXIT_SUCCESS);
    }

    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }
}
