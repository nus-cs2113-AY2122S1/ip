package duke.command;

/**
 * Represents an invalid command.
 */
public class InvalidCommand extends Command {

    // Message notifying user that their command is invalid.
    private final String INVALID_COMMAND_MSG = "Yikes, I do not recognise your input! (refer to 'help' command)";

    /**
     * Executes the command to print the {@code INVALID_COMMAND_MSG} to the user's terminal.
     *
     * @return the command result of the execution.
     */
    @Override
    public CommandResult executeCommand() {
        return new CommandResult(taskManager, INVALID_COMMAND_MSG, false, false);
    }
}
