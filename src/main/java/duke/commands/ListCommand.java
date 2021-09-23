package duke.commands;

import duke.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Lists the tasks in taskList
 */
public class ListCommand extends Command {
    /**
     * Prints the tasks in taskList in order
     * @param taskList the list to be printed
     * @param ui prints the tasks in taskList
     * @param storage loading and saving taskList
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        int userInputsCount = taskList.getListSize();

        ui.printListTask();

        for (int i = 1; i <= userInputsCount; i++) {
            ui.printToUser("    ", Integer.toString(i), ".", taskList.getTask(i - 1).toString());
        }
    }
}
