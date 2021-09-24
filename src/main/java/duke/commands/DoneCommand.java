package duke.commands;

import duke.datasaver.DataManager;
import duke.task.TaskList;
import duke.ui.Ui;

public class DoneCommand extends Command {

    private final int taskIndex;

    public DoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the marking of a task as done by passing the user input to the task list to be marked done.
     * An error message is printed if the task ID entered by the user is non-numeric, lacking from the command
     * or not in the task list.
     *
     * @param taskList  {@code TaskList} containing the task to be mark done
     * @param dataManager {@code DataManager} which saves the updated done status of the task to Duke's storage file
     */
    @Override
    public void execute(TaskList taskList, DataManager dataManager) {
        try {
            taskList.markTaskDone(taskIndex);
            dataManager.saveData(taskList);
        } catch (IndexOutOfBoundsException ioobe) {
            Ui.printTaskNotInListMessage();
        }
    }
}
