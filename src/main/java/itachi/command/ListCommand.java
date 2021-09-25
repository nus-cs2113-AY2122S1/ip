package itachi.command;

import itachi.Storage;
import itachi.TaskList;

public class ListCommand extends Command {

    @Override
    public void executeUserCommand(TaskList taskList, Storage storage) {
        taskList.listTasks();
    }
}
