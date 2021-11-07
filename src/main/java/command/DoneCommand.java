package command;

import exceptions.DukeInvalidArgumentException;
import storage.Storage;
import task.TaskList;

/**
 * Represents the done command given by user
 * to mark a task as complete.
 */
public class DoneCommand extends Command {
    private int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Marks task in list as done.
     *
     * @param tasksList List of tasks
     * @param storage Storage object to save the data
     */
    @Override
    public void execute(TaskList tasksList, Storage storage) throws DukeInvalidArgumentException {
        try {
            tasksList.markTaskAsDone(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeInvalidArgumentException();
        }
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
