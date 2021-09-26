package commands;

import tasklist.TaskList;
import ui.Ui;

public class ExitCommand extends Command{
    private static final Ui ui = new Ui();

    /**
     * Changes isExit attribute to true and shows the exit message.
     *
     * @param tasks task list that is included so exit command can be extended from command.
     */
    @Override
    public void execute(TaskList tasks) {
        ui.showExitMessage();
        isExit = true;
    }
}
