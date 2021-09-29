package commands;

import static ui.Ui.USER_GUIDE;

/**
 * Shows help instructions.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = "help: Shows program usage instructions.\n"
            + "\tExample: help\n";

    @Override
    public CommandResult execute() {
        return new CommandResult(USER_GUIDE);
    }
}
