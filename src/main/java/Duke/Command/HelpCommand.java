package Duke.Command;

import Duke.Storage.Storage;
import Duke.Tasks.TaskList;
import Duke.Ui.Ui;

public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";
    public static final String COMMAND_DESCRIPTION = COMMAND_WORD
            + ": show help message for the programme.\n"
            + " Example: " + COMMAND_WORD;

    /**
     * Execute the help command by showing the usage of all the commands
     *
     * @param tasks TaskList the command to be executed on
     * @param ui Ui used for execution
     * @param storage Storage which the command may make change on
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showHelpMessage();
    }
}
