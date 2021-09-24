package commands;

import tasklist.TaskList;
import ui.Ui;

public class HelpCommand extends Command{
    private static final Ui ui = new Ui();

    @Override
    public void execute(TaskList tasks) {
        ui.showHelpMessage();
    }
}
