package duke.logic.commands;

import static duke.ui.Ui.MESSAGE_COMMAND_LIST;
import static duke.ui.Ui.QUOTATION;

public class CommandListCommand extends Command {

    public static final String COMMAND_WORD = "commands";
    public static final String MESSAGE_COMMAND_FORMAT = QUOTATION + COMMAND_WORD + QUOTATION;
    public static final String MESSAGE_COMMAND_DESCRIPTION = MESSAGE_COMMAND_FORMAT + " : See this list of commands again";

    @Override
    public CommandResult execute() {
        return new CommandResult(MESSAGE_COMMAND_LIST);
    }
}
