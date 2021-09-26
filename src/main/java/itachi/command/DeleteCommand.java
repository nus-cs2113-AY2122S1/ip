package itachi.command;

import itachi.Storage;
import itachi.TaskList;
import itachi.exception.ItachiException;

/**
 * Deletes an existing task in the task list and stores the data
 */
public class DeleteCommand extends Command {
    protected int indexOfDelete;

    /**
     * Constructor initialises the command object with the index to be deleted
     *
     * @param indexOfDelete is the index of the task to be removed from the task list
     */
    public DeleteCommand(int indexOfDelete) {
        this.indexOfDelete = indexOfDelete;
    }

    /**
     * Executes Delete task command to delete the task of the given index in the task list
     *
     * @param taskList is an object of TaskList that consists of task operations
     * @param storage  is an object of Storage that saves current list of tasks
     * @throws ItachiException if any error occurs within deleting tasks or saving data
     */
    @Override
    public void executeUserCommand(TaskList taskList, Storage storage) throws ItachiException {
        taskList.deleteTask(indexOfDelete);
        storage.saveData();
    }
}
