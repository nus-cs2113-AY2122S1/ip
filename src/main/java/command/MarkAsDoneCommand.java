package command;

import exception.AustinException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

import java.io.IOException;

/**
 * Marks the task as "done".
 */
public class MarkAsDoneCommand extends Command {
    public static final String COMMAND_KEYWORD = "done";
    private int taskIndex;

    /**
     * Constructs MarkAsDoneCommand
     *
     * @param taskIndex Index of the task to be marked as "done"
     */
    public MarkAsDoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes MarkAsDoneCommand by marking the task as "done"
     * and prints a confirmation message to the console.
     *
     * @param tasks The task list which stores the task
     * @param ui In charge of informing the user that the command has been executed
     * @param storage In charge of reading and writing to data file
     * @throws AustinException If the task is already completed
     * @throws IOException If there is an error in accessing the file
     * @throws IndexOutOfBoundsException If the task index is out of range
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AustinException,
            IOException, IndexOutOfBoundsException {
        tasks.markAsDone(taskIndex);
        storage.updateFile(tasks);
        ui.acknowledgeDone(taskIndex);
    }
}
