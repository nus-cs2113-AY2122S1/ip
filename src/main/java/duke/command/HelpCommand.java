package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class HelpCommand extends Command {

    /**
     * A help command execute method which will print help message using ui
     *
     * @param tasks   TaskList Class which stores all tasks related information such as task array and handle all task
     *                related operations
     * @param ui      UI class which handles all user interactions
     * @param storage Storage class which allows all loading and saving of task information
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printHelpMessage();
    }

}
