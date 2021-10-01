package duke.logic.commands;

import duke.ui.Ui;

/**
 *  Represents the command that when executed, lists the possible commands that the user can execute along with their
 *  description and format.
 */
public class CommandListCommand extends Command {
    public static final String COMMAND_WORD = "commands";
    public static final String MESSAGE_COMMAND_FORMAT = Ui.QUOTATION + COMMAND_WORD + Ui.QUOTATION;
    public static final String MESSAGE_COMMAND_DESCRIPTION = MESSAGE_COMMAND_FORMAT + " : See this list of commands again";

    @Override
    public CommandResult execute() {
        return new CommandResult(Ui.MESSAGE_COMMAND_LIST);
    }
}
