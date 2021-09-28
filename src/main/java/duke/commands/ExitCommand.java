package duke.commands;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Terminated the program.
 */
public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exits the program.\n"
            + "⮞ Example: " + COMMAND_WORD;

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExit();
    }

    /**
     * Sets the flag that indicates whether it exits the program or not to true.
     *
     * @return <code>true</code>.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
