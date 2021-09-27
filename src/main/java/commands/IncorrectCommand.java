package commands;

/**
 * A Command Class that send an error message to the Ui
 */
public class IncorrectCommand extends Command{

    private final String message;

    public IncorrectCommand(String message) {
        this.message = message;
    }

    /**
     * Sends the error message to the Ui
     *
     * @return A CommandResult that tells the Ui to print the error message.
     */
    @Override
    public CommandResult execute() {
        return new CommandResult(message,PrintOptions.DEFAULT);
    }
}
