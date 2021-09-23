package duke.command;

import duke.Storage;
import duke.TaskList;

/**
 * Class that represents and defines the behaviour of the List command
 */
public class ListCommand extends Command{

    /**
     * Executes the command and lists every task in the task list
     * @param taskList Task Manager that executes task based on the given command
     * @param storage Stores User data in a csv file
     */
    @Override
    public void executeCommand(TaskList taskList, Storage storage) {
        taskList.printTasks();
    }
}
