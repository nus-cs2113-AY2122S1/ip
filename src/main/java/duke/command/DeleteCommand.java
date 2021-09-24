package duke.command;

import duke.datasaver.DataManager;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {

    private final int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the deletion of a task py passing {@code userInput} to the {@code TaskList}.
     * An error message is printed if the task ID entered by the user is non-numeric, lacking from the command
     * or not in the task list.
     *
     * @param taskList {@code TaskList} containing the task to be deleted
     * @param dataManager {@code DataManager} which removes the deleted task from Duke's storage
     */
    @Override
    public void execute(TaskList taskList, DataManager dataManager) {
        try {
            taskList.deleteTask(taskIndex);
            dataManager.saveData(taskList.getTaskList());
        } catch (IndexOutOfBoundsException ioobe) {
            Ui.printTaskNotInListMessage();
        }
    }
}
