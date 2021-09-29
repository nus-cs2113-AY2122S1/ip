package duke.logic.commands;

import duke.ui.Ui;

/**
 * Represents the command that when executed, signals to the Main class to exit the program.
 */
public class ByeCommand extends Command {

    public static final String COMMAND_WORD = "bye";
    public static final String MESSAGE_COMMAND_FORMAT = Ui.QUOTATION + COMMAND_WORD + Ui.QUOTATION;
    public static final String MESSAGE_COMMAND_DESCRIPTION = MESSAGE_COMMAND_FORMAT + " : Stop Dude :(";
    private static final String MESSAGE_SUCCESS = "Stopping Dude... :(";

    @Override
    public CommandResult execute() {
        return new CommandResult(MESSAGE_SUCCESS);
    }

    public static boolean isBye(Command command) {
        return command instanceof ByeCommand;
    }
}
