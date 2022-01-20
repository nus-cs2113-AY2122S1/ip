package unker.command;

import unker.task.Task;
import unker.task.Unker;
import unker.task.storage.TasksFileException;
import unker.ui.UI;

public abstract class CreateTaskCommand extends Command {

    protected CreateTaskCommand(String name, String format) {
        super(name, format);
    }

    /**
     * Adds a task into the Unker task manager.
     * 
     * @param ui The UI to print messages to.
     * @param unker The task manager that handles all the tasks.
     * @param task The task to add.
     * @throws InvalidCommandException When the task is null (typically when the input is invalid).
     */
    protected void addTask(UI ui, Unker unker, Task task) throws InvalidCommandException, TasksFileException {
        if (task == null) {
            throw new InvalidCommandException(INVALID_FORMAT_MESSAGE, this);
        }
        unker.addTask(task);
        ui.printSection(ADDED_TASK_MESSAGE, "\t" + task);
    }
    
}
