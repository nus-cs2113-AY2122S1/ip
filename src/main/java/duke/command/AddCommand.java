package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class AddCommand extends Command {
    Task task = null;

    /**
     * Constructor for AddCommand, command to add task to taskManager
     *
     * @param task the Task to be added to taskManager
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Calls the addTask method in taskManager to add the specified task
     *
     * @param taskList The taskManager that the task will be added to
     * @param ui The ui class instance that will print out the completion message
     * @throws DukeException If the maximum number of tasks has been reached
     */
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) throws DukeException {
        taskList.addTask(task, ui);
        storage.saveData(taskList);
    }
}
