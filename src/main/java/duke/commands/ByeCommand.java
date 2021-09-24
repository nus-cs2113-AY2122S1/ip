package duke.commands;

import duke.storage.DataStorage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ByeCommand extends Command {

    @Override
    public void execute(TaskList taskList, DataStorage dataStorage) {
        Ui.printByeMessage();
        System.exit(0);
    }
}
