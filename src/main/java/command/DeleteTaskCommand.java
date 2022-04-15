package command;

import exception.AustinException;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

import java.io.IOException;

/**
 * Deletes a task in the list and in the file
 */
public class DeleteTaskCommand extends Command {
    public static final String COMMAND_KEYWORD = "delete";
    private int taskIndex;

    /**
     * Constructs DeleteTaskCommand
     *
     * @param taskIndex Index of the task to be deleted
     */
    public DeleteTaskCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes DeleteTaskCommand by deleting the task in the list
     * and in the file. It also prints a confirmation message to the console.
     *
     * @param tasks The task list which stores the task
     * @param ui In charge of informing the user that the command has been executed
     * @param storage In charge of reading and writing to data file
     * @throws IOException If there is an error accessing the file
     * @throws IndexOutOfBoundsException If the task index is out of range
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws
            IOException, IndexOutOfBoundsException {
        Task removedTask = tasks.deleteTask(taskIndex);
        storage.updateFile(tasks);
        ui.acknowledgeDelete(removedTask);
    }
}
