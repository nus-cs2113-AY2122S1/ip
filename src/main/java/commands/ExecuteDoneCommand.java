package commands;
import exception.DukeException;
import storage.Storage;
import task.type.TaskList;

public class ExecuteDoneCommand extends Command {
    private int index;

    /**
     * Constructor
     *
     * @param index The number of the Task which is to be marked as done.
     */
    public ExecuteDoneCommand(int index) {
        this.index = index;
    }

    /**
     * To mark the specific task in the list as done.
     *
     * @param tasksList Object of TaskList.
     * @param storage Object of Storage.
     */
    @Override
    public void execute(TaskList tasksList, Storage storage) throws DukeException {
        try {
            tasksList.markTaskAsDone(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException();
        }
    }

    /**
     * To check whether to exit the application
     *
     * @return false To take input from user again.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
