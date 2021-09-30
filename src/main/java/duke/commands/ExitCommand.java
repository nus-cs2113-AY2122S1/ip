package duke.commands;

import duke.common.Messages;
import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.TextUi;

/**
 * Exits the program.
 */
public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exits the program. "
            + "\n|| "
            + "Example: " + COMMAND_WORD + "\n||";

    /**
     * Shows an exit message to the user and exits the program.
     *
     * @param tasks a task list that contains all the tasks
     * @param ui accesses format and messages to show to the user
     * @param storage accesses a text file which stores the task list
     */
    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        ui.showToUser(ui.DASHES, Messages.MESSAGE_GOODBYE, ui.DASHES);
        isExit = true;
    }
}