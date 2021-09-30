package duke.commands;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.TextUi;

import java.io.IOException;

/**
 * Deletes every task in the task list.
 */
public class ClearCommand extends Command {
    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes every task in the task list. "
            + "\n"
            + "Example: " + COMMAND_WORD
            + "\n";
    public static final String MESSAGE_SUCCESSFUL_CLEAR = "Task list cleared! Real empty here now...";

    /**
     * Deletes every task from the TaskList and the storage file.
     * Shows the user a message for successful deletion.
     *
     * @param tasks a task list that contains all the tasks
     * @param ui accesses format and messages to show to the user
     * @param storage accesses a text file which stores the task list
     */
    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        try {
            tasks.clearList();
            storage.clearFile();
            ui.showToUser(MESSAGE_SUCCESSFUL_CLEAR);
        } catch (IOException e) {
            System.out.println("IO Error!");
        }
    }
}
