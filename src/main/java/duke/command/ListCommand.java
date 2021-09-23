package duke.command;

import duke.program.LizUi;
import duke.program.TaskList;

public class ListCommand extends Command {

    /**
     * Executes command that prints out either the entire task list, or the task list of matching tasks.
     * @param tasks TaskList of all tasks.
     * @param ui ui object of Duke.
     */
    @Override
    public void executeCommand(TaskList tasks, LizUi ui) {
        ui.printTaskList(tasks.getTaskCount(), tasks.getTaskList(), false);
    }

}
