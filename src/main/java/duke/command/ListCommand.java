package duke.command;

import duke.Storage;
import duke.TaskList;

public class ListCommand extends Command {

    /**
     * Calls the printTasks method in taskManager to list out its Tasks
     *
     * @param taskList The taskManager that will be read
     */
    @Override
    public void execute(TaskList taskList, Storage storage) {
        taskList.printTasks();
    }
}
