package commands;

import static common.Messages.USER_GUIDE;

public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = "\nhelp: Shows program usage instructions.\n"
            + "\tExample: help\n";

    @Override
    public CommandResult execute() {
        return new CommandResult(USER_GUIDE);
    }
}
