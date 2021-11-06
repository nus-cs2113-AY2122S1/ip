package duke.commands;

import duke.storage.DataStorage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents the {@code bye} command to exit the program.
 */
public class ByeCommand extends Command {

    /**
     * Prints a bye message and exits the program.
     */
    @Override
    public void execute(TaskList taskList, DataStorage dataStorage) {
        Ui.printByeMessage();
        System.exit(0);
    }
}
