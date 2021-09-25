package commands;

public class ExitCommand extends Command{

    public static final String GOODBYE_MESSAGE = "Bye, hope to see you again soon! :)";

    public CommandResult execute() {
        return new CommandResult(GOODBYE_MESSAGE);
    }
}
