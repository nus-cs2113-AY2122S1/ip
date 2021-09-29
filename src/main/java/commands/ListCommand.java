package commands;

import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

public class ListCommand extends Command {

    /**
     * Displays the current task list to the user.
     *
     * @param tasks task list to be accessed.
     * @param ui Access to messages.
     * @param storage storage access that allows list to extend command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.getListSize() == 0) {
            ui.showEmptyListMessage();
        } else {
            ui.showListMessage();
            for (int i = 1; i <= tasks.getListSize(); i++) {
                System.out.println(" " + i + "." + tasks.getTaskFromList(i - 1));
            }
            ui.showLines();
        }
    }
}
