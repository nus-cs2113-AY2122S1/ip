package Duke.Command;

import Duke.Ui.Ui;
import Duke.Storage.Storage;
import Duke.Tasks.TaskList;

public class ClearCommand extends Command {
    public static final String COMMAND_WORD = "clear";
    public static final String COMMAND_DESCRIPTION = COMMAND_WORD
            + ": clear all tasks in the current list.\n"
            + " Example: " + COMMAND_WORD;

    /**
     * Execute the clear command by clearing the tasks in TaskList
     *
     * @param tasks TaskList the command to be executed on
     * @param ui Ui used for execution
     * @param storage Storage which the command may make change on
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printClearMessage();
        tasks.clearTasks();
        storage.save(tasks.getTasks());
    }
}
