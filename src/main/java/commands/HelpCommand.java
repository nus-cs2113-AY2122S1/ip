package commands;

import tasklist.TaskList;
import ui.Ui;

public class HelpCommand extends Command{
    private static final Ui ui = new Ui();

    /**
     * Shows the help message.
     *
     * @param tasks task list that is included so help command can be extended from command.
     */
    @Override
    public void execute(TaskList tasks) {
        ui.showHelpMessage();
    }
}
