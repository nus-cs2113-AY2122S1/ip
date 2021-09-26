package duke.command;

import duke.system.Storage;
import duke.system.TaskList;
import duke.system.Ui;

/**
 * Represents a command to exit the program
 */
public class ExitCommand extends Command {

    /**
     * Say goodbye to the user.
     * @param tasks   is not used
     * @param ui      the Ui object responsible for printing messages
     * @param storage is not used
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printBye();
    }

    /**
     * Indicate whether to exit and do not take in any new command.
     * @return true for exit command to let the program exit
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
