package duke.commands;

public class ExitCommand extends Command {

    private static final String EXIT_MESSAGE = "Bye! Have a great day ahead and see you again soon.";

    public ExitCommand() {
        super();
    }

    @Override
    public CommandResult executeCommand() {
        CommandResult result = new CommandResult(EXIT_MESSAGE);
        return result;
    }
}
