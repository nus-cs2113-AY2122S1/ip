package duke.logic.commands;

import static duke.ui.Ui.QUOTATION;

public class ByeCommand extends Command {

    public static final String COMMAND_WORD = "bye";
    public static final String MESSAGE_COMMAND_FORMAT = QUOTATION + COMMAND_WORD + QUOTATION;
    public static final String MESSAGE_COMMAND_DESCRIPTION = MESSAGE_COMMAND_FORMAT + " : Stop Dude :(";
    private static final String MESSAGE_SUCCESS = "Bye! Hope to see you again soon! ~^u^~ ";

    @Override
    public CommandResult execute() {
        return new CommandResult(MESSAGE_SUCCESS);
    }

    public static boolean isBye(Command command) {
        return command instanceof ByeCommand;
    }
}
