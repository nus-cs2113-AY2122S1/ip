package Duke.Command;

import Duke.Storage.Storage;
import Duke.Tasks.TaskList;
import Duke.Ui.Ui;

public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";
    public static final String COMMAND_DESCRIPTION = COMMAND_WORD
            + ": show help message for the programme.\n"
            + " Example: " + COMMAND_WORD;

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showHelpMessage();
    }
}
