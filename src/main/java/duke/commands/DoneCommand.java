package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Marks a task in taskList as done
 */
public class DoneCommand extends Command {
    protected int doneIndex;

    public DoneCommand(int doneIndex) {
        this.doneIndex = doneIndex - 1;
    }

    /**
     * Marks a task as done by the specified index from taskList a prints a message when done
     * @param taskList the list of access task to be marked done
     * @param ui prints the message after tasks is marked as done
     * @param storage loading and saving taskList
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.markDone(this.doneIndex);
        ui.printFinishedTask();
        ui.printToUser("        ", taskList.getTask(this.doneIndex).toString());
    }
}
