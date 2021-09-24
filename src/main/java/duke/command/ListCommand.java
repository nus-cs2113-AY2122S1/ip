package duke.command;

import duke.Storage;
import duke.TaskList;

public class ListCommand extends Command {

    @Override
    public void executeUserCommand(TaskList taskList, Storage storage) {
        taskList.listTasks();
    }
}
