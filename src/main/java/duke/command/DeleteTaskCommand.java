package duke.command;

import duke.Storage;
import duke.DukeException;
import duke.TaskList;

/**
 * Class that represents and defines the behaviour of the Delete command
 */
public class DeleteTaskCommand extends Command{
    protected int taskIndex;

    /**
     * Constructor initialises the Delete task command with the index of the task to be deleted from the task list
     * @param taskIndex Index of task to be removed from the task list
     */
    public DeleteTaskCommand(int taskIndex) { this.taskIndex = taskIndex; }

    /**
     * Executes Delete task command and deletes the given task from the task list
     * @param taskList Task Manager that executes task based on the given command
     * @param storage Stores User data in a csv file
     * @throws DukeException Throws any error messages from deleteTask or saveData functions
     */
    @Override
    public void executeCommand(TaskList taskList, Storage storage) throws DukeException {
        taskList.deleteTask(taskIndex);
        storage.saveData(taskList);
    }
}
