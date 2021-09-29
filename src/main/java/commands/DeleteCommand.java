package commands;
import storage.Storage;
import task.type.TaskList;

public class DeleteCommand extends Command {
    private int indexOfTask;

    /**
     * Constructor
     *
     * @param indexOfTask The number of the Task which is to be deleted.
     */
    public DeleteCommand(int indexOfTask) {
        this.indexOfTask = indexOfTask;
    }

    /**
     * Executes deletion of task
     *
     * @param tasksList Object of TaskList.
     * @param storage Object of Storage.
     */
    @Override
    public void execute(TaskList tasksList, Storage storage) {
        tasksList.deleteTaskFromList(indexOfTask);
    }

    /**
     * To check whether to exit the application
     *
     * @return false To take inputs from user again.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
