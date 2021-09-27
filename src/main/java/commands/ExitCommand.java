package commands;

public class ExitCommand extends Command{

    public static final String GOODBYE_MESSAGE = "Bye, hope to see you again soon! :)";
    public static final String EXIT_COMMAND = "bye";

    public CommandResult execute() {
        return new CommandResult(GOODBYE_MESSAGE,PrintOptions.DEFAULT);
    }
}
