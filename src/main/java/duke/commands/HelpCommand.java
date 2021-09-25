package duke.commands;

import duke.storage.DataStorage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents the {@code help} command.
 */
public class HelpCommand extends Command {

    /**
     * Executes the printing of a list of recognizable commands along with their formats.
     */
    @Override
    public void execute(TaskList taskList, DataStorage dataStorage) {
        Ui.printHelp();
    }
}
