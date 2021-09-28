package Duke.Command;

import Duke.Ui.Ui;
import Duke.Storage.Storage;
import Duke.Tasks.TaskList;

public class ClearCommand extends Command {
    public static final String COMMAND_WORD = "clear";
    public static final String COMMAND_DESCRIPTION = COMMAND_WORD
            + ": clear all tasks in the current list.\n"
            + " Example: " + COMMAND_WORD;

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printClearMessage();
        tasks.clearTasks();
        storage.save(tasks.getTasks());
    }
}
