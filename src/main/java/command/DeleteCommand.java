package command;

import storage.Storage;
import task.TaskList;

/**
 * Represents the delete command given by user to delete a task.
 */
public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes task from list.
     *
     * @param tasksList List of tasks
     * @param storage Storage object to save the data
     */
    @Override
    public void execute(TaskList tasksList, Storage storage) {
        // index - 1 will be done at task.TaskList.deleteFromList
        tasksList.deleteFromList(index);
    }

    /**
     * Returns a boolean to check whether to exit application or not.
     *
     * @return false To continue taking user commands
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
