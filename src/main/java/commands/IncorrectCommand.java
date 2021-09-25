package commands;

public class IncorrectCommand extends Command{

    public static final String DEFAULT_ERROR_MESSAGE = "Oops, something went wrong!";

    public CommandResult execute() {
        return new CommandResult(DEFAULT_ERROR_MESSAGE);
    }
}
