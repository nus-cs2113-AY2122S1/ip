package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {

    /**
     * Calls the printTasks method in taskManager to list out its Tasks
     *
     * @param taskList The taskManager that will be read
     * @param ui The ui class instance that will print out the completion message
     */
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        taskList.printTasks(ui);
    }
}
