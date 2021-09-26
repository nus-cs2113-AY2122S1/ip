package commands;

import tasklist.TaskList;
import ui.Ui;

public class ListCommand extends Command {
    protected Ui ui = new Ui();

    /**
     * Displays the current task list to the user.
     *
     * @param tasks task list to be accessed.
     */
    @Override
    public void execute(TaskList tasks) {
        if (tasks.getListSize() == 0) {
            ui.showEmptyListMessage();
        } else {
            ui.showLines();
            for (int i = 1; i <= tasks.getListSize(); i++) {
                System.out.println(i + "." + tasks.getTaskFromList(i - 1));
            }
            ui.showLines();
        }
    }
}
