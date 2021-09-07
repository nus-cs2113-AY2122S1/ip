package command;

import task.TaskManager;
import ui.UI;

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
     * Uses the TaskManager object to mark the task as done internally, and then prints a message using the UI object
     * to let the user know the task is marked done.
     *
     * @param taskManager the TaskManager type object used to handle internal task related operations
     * @param ui the UI type object used to handle i/o for taro
     */
    @Override
    public void execute(TaskManager taskManager, UI ui) {
        taskManager.markAsDone(taskIndex);
        ui.printMarkedDoneMessage(taskManager.getTasks()[taskIndex - 1]);
    }
}
