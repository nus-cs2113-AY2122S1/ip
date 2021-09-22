package duke.commands;

import duke.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        int userInputsCount = taskList.getListSize();

        ui.printListTask();

        for (int i = 1; i <= userInputsCount; i++) {
            ui.printToUser("    ", Integer.toString(i), ".", taskList.getTask(i - 1).toString());
        }
    }
}
