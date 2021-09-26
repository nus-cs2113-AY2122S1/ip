package duke.command;

/**
 * Represents a command to terminate the Duke program.
 */
public class TerminateCommand extends Command {

    // Message displayed prior to Duke terminating.
    private final String EXIT_MSG = "Come back soon, I'm still hungry \uD83D\uDE0B";

    /**
     * Executes the command to terminate Duke and print the {@code EXIT_MSG} to the user's terminal.
     *
     * @return the command result of the execution.
     */
    @Override
    public CommandResult executeCommand() {
        return new CommandResult(EXIT_MSG, false, true);
    }

}
