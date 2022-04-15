package duke.command;

import duke.task.TaskManager;
import duke.ui.Ui;

/**
 * Command for quitting duke.
 */
public class QuitCommand extends Command {
    /**
     * Executes the QuitCommand by setting isBye as true, and
     * printing a farewell message.
     *
     * @param taskManager TaskManager object not used here.
     * @param ui          Ui object used to print a farewell message.
     */
    @Override
    public void execute(TaskManager taskManager, Ui ui) {
        isBye = true;
        ui.printFarewell();
    }
}
