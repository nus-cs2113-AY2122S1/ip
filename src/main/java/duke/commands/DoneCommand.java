package duke.commands;

import duke.data.TaskList;
import duke.exception.DukeOutOfRangeException;
import duke.exception.StorageOperationException;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Marks a task as done using its last displayed index from the list.
 */
public class DoneCommand extends Command{
    private final int doneIndex;
    public static final String COMMAND_WORD = "done";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Mark the task identified by the index number used in the last task listing as done.\n"
            + "✪ Parameters: INDEX\n"
            + "⮞ Example: " + COMMAND_WORD + " 1";

    public DoneCommand(int index) {
        this.doneIndex = index;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeOutOfRangeException, StorageOperationException {
        if (doneIndex > tasks.getSize()) {
            throw new DukeOutOfRangeException("Please enter a valid index of task :-(");
        }

        tasks.doneTask(doneIndex - 1);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.getTask(doneIndex - 1));
        storage.save(tasks);
    }
}
