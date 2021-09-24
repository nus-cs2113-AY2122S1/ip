package duke.commands;

import duke.storage.DataStorage;
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
     * @param dataStorage {@code DataStorage} which removes the deleted task from Duke's storage
     */
    @Override
    public void execute(TaskList taskList, DataStorage dataStorage) {
        try {
            taskList.deleteTask(taskIndex);
            dataStorage.saveData(taskList);
        } catch (IndexOutOfBoundsException ioobe) {
            Ui.printTaskNotInListMessage();
        }
    }
}
