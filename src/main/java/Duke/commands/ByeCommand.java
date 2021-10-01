package Duke.commands;

import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;

public class ByeCommand extends Command {

    /**
     * Shows user bye message
     *
     * @param tasks TaskList containing Task
     * @param ui User interface
     * @param storage File management interface
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showBye();
    }

    /**
     * Sets exit boolean to true, so that while loop terminates and program ends
     *
     * @return Boolean for while loop to exit
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
