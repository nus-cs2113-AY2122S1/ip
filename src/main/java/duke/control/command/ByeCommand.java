package duke.control.command;

import duke.control.Storage;
import duke.control.TaskList;
import duke.control.Ui;

public class ByeCommand extends Command {

    @Override
    public void executeCommand(TaskList list, String input, Storage storage) {
        Ui.printExitMessage();
        Storage.saveData(list);
    }
}
