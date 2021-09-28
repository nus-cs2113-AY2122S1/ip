package Duke.Command;

import Duke.Storage.Storage;
import Duke.Ui.Ui;
import Duke.Tasks.TaskList;
import Duke.DukeException.DukeException;

public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";

    public static final String COMMAND_DESCRIPTION = COMMAND_WORD
            + ": Delete the task identified by the index number used in the current list.\n"
            + " Parameters: INDEX\n"
            + " Example: " + COMMAND_WORD + " 1";

    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.deleteTask(this.index);
        ui.printDeleteMessage();
        ui.printNumOfTasks(tasks);
        storage.save(tasks.getTasks());
    }
}
