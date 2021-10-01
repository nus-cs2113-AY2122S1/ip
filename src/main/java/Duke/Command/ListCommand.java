package Duke.Command;

import Duke.Ui.Ui;
import Duke.Storage.Storage;
import Duke.Tasks.TaskList;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public static final String COMMAND_DESCRIPTION = COMMAND_WORD
            + ": display all tasks in the current list.\n"
            + " Example: " + COMMAND_WORD;

    /**
     * Execute the list command by showing the TaskList.
     *
     * @param tasks TaskList the command to be executed on.
     * @param ui Ui used for execution
     * @param storage Storage which the command may make change on.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (!tasks.isEmpty()) {
            ui.printListMessage();
            tasks.printTasks();
        } else {
            ui.printListEmptyMessage();
        }
    }
}
