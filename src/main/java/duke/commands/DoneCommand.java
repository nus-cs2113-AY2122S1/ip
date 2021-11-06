package duke.commands;

import duke.storage.DataStorage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to mark a {@code Task} as done.
 */
public class DoneCommand extends Command {

    private final int taskIndex;

    public DoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the marking of a task in {@code taskList} as done. Saves the updated {@code taskList} to Duke's
     * {@code DataStorage}.
     *
     * @param taskList  {@code TaskList} containing the task to be mark done
     * @param dataStorage {@code DataStorage} which saves the updated done status of the task to Duke's storage file
     */
    @Override
    public void execute(TaskList taskList, DataStorage dataStorage) {
        try {
            taskList.markTaskDone(taskIndex);
            dataStorage.saveData(taskList);
        } catch (IndexOutOfBoundsException ioobe) {
            Ui.printTaskNotInListMessage();
        }
    }
}
