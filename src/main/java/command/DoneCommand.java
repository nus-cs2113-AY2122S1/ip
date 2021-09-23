package command;

import storage.Storage;
import task.Task;
import task.TaskList;
import ui.UI;

import java.io.IOException;

public class DoneCommand extends Command {
    private int taskIndex;

    /**
     * Initialises the done command so that the index of the task based on zero-based numbering is taskIndex - 1
     *
     * @param taskIndex the index of the task to be marked done as seen from the "list" command's printed output
     */
    public DoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Uses the TaskList object to mark the task as done internally, and then prints a message using the UI object
     * to let the user know the task is marked done. Ensures that the command is marked as done in the taro.txt data
     * file.
     *
     * @param taskList the TaskList type object used to handle internal task related operations
     * @param ui the UI type object used to handle i/o for taro
     * @param storage the object used for handling read/write operations to the taro.txt data file
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        try {
            Task doneTask = taskList.markAsDone(taskIndex);
            storage.writeDoneTask(taskIndex);
            ui.printMarkedDoneMessage(doneTask);
        } catch (IOException e) {
            ui.printString("there was an error while marking that task as done.");
        }
    }
}
