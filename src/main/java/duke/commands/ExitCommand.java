package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command Class for exiting the programme.
 */
public class ExitCommand extends Command {
    /**
     * Initializes new ExitCommand object.
     * Sets isExit = true.
     */
    public ExitCommand() {
        this.isExit = true;
        this.fullCommand = "";
    }

    /**
     * Outputs exit message to the user.
     * @param tasks TaskList object of all tasks in the programme
     * @param ui Ui object for calling Ui methods
     * @param storage Storage object for writing to memory
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showBye();
    }

}
