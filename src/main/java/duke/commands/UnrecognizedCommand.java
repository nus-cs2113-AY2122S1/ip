package duke.commands;

import duke.storage.DataStorage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents any command which cannot be recognized by Duke.
 */
public class UnrecognizedCommand extends Command {

    /**
     * Executes the printing of a message informing the user that an unrecognized command has been entered.
     */
    @Override
    public void execute(TaskList taskList, DataStorage dataStorage) {
        Ui.printUnrecognizedCommandMessage();
    }
}
