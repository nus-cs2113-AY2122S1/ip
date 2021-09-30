package duke.command;

import duke.data.task.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;


public class HelpCommand extends Command {
    /**
     * Show help information for users
     *
     * @param tasks   The list of tasks known
     * @param storage The storage that is used
     * @param ui      The ui that is used
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showHelp();
    }
}
