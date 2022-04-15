package duke.command;

import duke.program.LizUi;
import duke.program.TaskList;

public class InvalidCommand extends Command {

    /**
     * Executes command that prints an invalid command notice message to the console.
     * @param tasks TaskList of all tasks.
     * @param ui ui object of Duke.
     */
    @Override
    public void executeCommand(TaskList tasks, LizUi ui) {
        ui.printInvalidCommandMessage();
    }


}
