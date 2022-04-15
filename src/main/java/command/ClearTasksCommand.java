package command;

import exception.AustinException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

import java.io.IOException;

/**
 * Clears all the tasks in the list and in the file
 */
public class ClearTasksCommand extends Command {
    public static final String COMMAND_KEYWORD = "clear";

    /**
     * Executes ClearTasksCommand by removing all the tasks in the list and in the file.
     * Confirmation message is also printed to the console.
     /**
     * Executes AddDeadlineTaskCommand by adding a deadline task into the list
     * and into the file. It also prints the confirmation message into the console.
     *
     * @param tasks The task list which stores the task
     * @param ui In charge of informing the user that the command has been executed
     * @param storage In charge of reading and writing to data file
     * @throws AustinException If the list is empty
     * @throws IOException If there is an error accessing the file
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws
            AustinException, IOException {
        tasks.clearTasks();
        storage.clearFileContents();
        ui.acknowledgeClear();
    }
}
