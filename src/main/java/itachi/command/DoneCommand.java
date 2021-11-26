package itachi.command;

import itachi.Storage;
import itachi.TaskList;
import itachi.exception.ItachiException;

/**
 * Marks an existing task in the task list as done and stores the data
 */
public class DoneCommand extends Command {
    protected int indexOfDone;

    /**
     * Constructor initialises the command object with the index to be marked as done
     *
     * @param indexOfDone is the index of the task to be marked as done in the task list
     */
    public DoneCommand(int indexOfDone) {
        this.indexOfDone = indexOfDone;
    }

    /**
     * Executes Done task command to mark the task of the given index as done in the task list
     *
     * @param taskList is an object of TaskList that consists of task operations
     * @param storage  is an object of Storage that saves current list of tasks
     * @throws ItachiException if any error occurs within marking tasks as done or saving data
     */
    @Override
    public void executeUserCommand(TaskList taskList, Storage storage) throws ItachiException {
        taskList.doneTask(indexOfDone);
        storage.saveData();
    }
}
