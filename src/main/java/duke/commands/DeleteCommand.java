package duke.commands;

import duke.storage.DataStorage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents the command to delete a {@code Task}.
 */
public class DeleteCommand extends Command {

    private final int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the deletion of a task from {@code taskList}. Saves the updated {@code taskList} to Duke's
     * {@code DataStorage}.
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
