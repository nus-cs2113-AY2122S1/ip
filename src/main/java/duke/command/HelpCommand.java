package duke.command;

import duke.task.TaskDoneList;
import duke.task.TaskList;
import duke.ui.Ui;

public class HelpCommand extends Command {

    /**
     * Print the help message
     *
     * @param list The list of all tasks
     * @param doneList The list of all tasks which have been finished
     * @param ui The ui that is used
     */
    @Override
    public void executeCommand(TaskList list, TaskDoneList doneList, Ui ui) {
        ui.showHelpMessage(ui);
    }
}
