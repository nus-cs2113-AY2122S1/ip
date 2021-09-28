package duke.commands;

import duke.data.TaskList;
import duke.exception.DukeOutOfRangeException;
import duke.exception.StorageOperationException;
import duke.storage.Storage;
import duke.ui.Ui;

public class DeleteCommand extends Command {
    private final int deleteIndex;
    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the task identified by the index number used in the last task listing.\n"
            + "✪ Parameters: INDEX\n"
            + "⮞ Example: " + COMMAND_WORD + " 1";

    public DeleteCommand(int index) {
        this.deleteIndex = index;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeOutOfRangeException, StorageOperationException {
        if (deleteIndex > tasks.getSize()) {
            throw new DukeOutOfRangeException("Please enter a valid index of task :-(");
        }

        System.out.println("Noted. I've removed this task:\n" + tasks.getTask(deleteIndex - 1));
        tasks.deleteTask(deleteIndex - 1);
        System.out.println("Now you have " + tasks.getSize() + " tasks in the list.");
        storage.save(tasks);
    }
}
