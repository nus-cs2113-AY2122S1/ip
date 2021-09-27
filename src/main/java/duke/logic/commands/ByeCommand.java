package duke.logic.commands;

import static duke.ui.Ui.QUOTATION;

public class ByeCommand extends Command {

    public static final String COMMAND_WORD = "bye";
    public static final String MESSAGE_COMMAND_FORMAT = QUOTATION + COMMAND_WORD + QUOTATION;
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
