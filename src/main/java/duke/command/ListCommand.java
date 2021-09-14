package duke.command;

import duke.Storage;
import duke.TaskList;

public class ListCommand extends Command{

    @Override
    public void executeCommand(TaskList taskList, Storage storage) {
        taskList.printTasks();
    }
}
