package duke.commands;

import duke.Storage;
import duke.TasksList;
import duke.Ui;


public class ExitCommand extends Command {
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Exits the application.
     * @param taskList The <code>TaskList</code> used in the application.
     * @param ui The <code>Ui</code> used in the application.
     * @param storage The <code>Storage</code> used in the application.
     */
    public void execute(TasksList taskList, Ui ui, Storage storage) {
        ui.showGoodbye();
    };
}