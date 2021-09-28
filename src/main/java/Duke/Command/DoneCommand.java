package Duke.Command;

import Duke.Storage.Storage;
import Duke.Ui.Ui;
import Duke.Tasks.TaskList;
import Duke.DukeException.DukeException;

public class DoneCommand extends Command {
    private final int index;
    public static final String COMMAND_WORD = "done";
    public static final String COMMAND_DESCRIPTION = COMMAND_WORD
            + ": mark a task in the list as done.\n"
            + " Parameters: INDEX\n"
            + " Example: " + COMMAND_WORD + " 1";

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.printDoneMessage(tasks.setDone(this.index));
        storage.save(tasks.getTasks());
    }
}
