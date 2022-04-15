package Duke.commands;

import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;

public class ListCommand extends Command {

    /**
     * Shows user a list of all task in tasks
     *
     * @param tasks TaskList containing Task
     * @param ui User interface
     * @param storage File management system
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.showTaskList(ui);
    }
}
