
public class ExitCommand extends Command {
    public static final String MESSAGE = "bye";
    @Override
    public CommandResult execute() {
        return new CommandResult(MESSAGE);
    }

    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }
}
