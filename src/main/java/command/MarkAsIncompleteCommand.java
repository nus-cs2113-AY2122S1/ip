package command;

import exception.AustinException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

import java.io.IOException;

/**
 * Marks the task as incomplete.
 */
public class MarkAsIncompleteCommand extends Command {
    public static final String COMMAND_KEYWORD = "undo";
    private int taskIndex;

    /**
     * Constructs MarkAsIncomplete command.
     *
     * @param taskIndex Index of the task to be marked as incomplete
     */
    public MarkAsIncompleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes MarkAsIncomplete command by changing the status of the task
     * to incomplete and prints a confirmation message to the user.
     *
     * @param tasks The task list which stores the task
     * @param ui In charge of informing the user that the command has been executed
     * @param storage In charge of reading and writing to data file
     * @throws AustinException If the task is already marked as incomplete
     * @throws IOException If there is an error accessing the file
     * @throws IndexOutOfBoundsException If the task index is out of range
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AustinException,
            IOException, IndexOutOfBoundsException {
        tasks.markAsNotDone(taskIndex);
        storage.updateFile(tasks);
        ui.acknowledgeUndo(taskIndex);
    }

}
