package duke.commands;

import duke.storage.DataStorage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a command to add a {@code Task}.
 */
public class AddTaskCommand extends Command {

    private final Task toAdd;

    public AddTaskCommand(Task toAdd) {
        this.toAdd = toAdd;
    }

    /**
     * Executes the addition of a {@code Task} to {@code taskList}. Saves the updated {@code taskList} to Duke's
     * {@code DataStorage}.
     *
     * @param taskList {@code TaskList} where the task is to be added to
     * @param dataStorage {@code DataStorage} which saves the new deadline to Duke's storage
     */
    @Override
    public void execute(TaskList taskList, DataStorage dataStorage) {
        taskList.addTask(toAdd);
        dataStorage.saveData(taskList);
    }
}
