package commands;

/**
 * A Command class that passes the exit message to the Ui
 */
public class ExitCommand extends Command{

    public static final String GOODBYE_MESSAGE = "Bye, hope to see you again soon! :)";
    public static final String EXIT_COMMAND = "bye";

    /**
     * Sends the exit message to the Ui
     *
     * @return A CommandResult that tells the Ui to print the exit message.
     */
    @Override
    public CommandResult execute() {
        return new CommandResult(GOODBYE_MESSAGE,PrintOptions.DEFAULT);
    }
}
