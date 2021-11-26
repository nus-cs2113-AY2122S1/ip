package itachi.command;

import itachi.Storage;
import itachi.TaskList;

/**
 * Lists down the tasks present in the list array
 */
public class ListCommand extends Command {

    /**
     * Executes List task command to list all the tasks from the task list
     *
     * @param taskList is an object of TaskList that consists of task operations
     * @param storage  is an object of Storage that saves current list of tasks
     */
    @Override
    public void executeUserCommand(TaskList taskList, Storage storage) {
        taskList.listTasks();
    }
}
