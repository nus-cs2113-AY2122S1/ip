package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class HelpCommand extends Command {
    /**
     * Prints the help menu that shows the command formats
     * @param taskList list of tasks
     * @param ui prints the help menu
     * @param storage saving and loading taskList
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printHelp();
    }
}
