package duke.command;

import duke.program.LizUi;
import duke.program.TaskList;

public class ByeCommand extends Command {

    /**
     * Executes command that prints an exit message to the console.
     * @param tasks TaskList of all tasks.
     * @param ui ui object of Duke.
     */
    @Override
    public void executeCommand(TaskList tasks, LizUi ui) {
        ui.printExitMessage();
    }

    /**
     * Checks if exit command is called.
     * @return True if exit command is called. False otherwise.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
