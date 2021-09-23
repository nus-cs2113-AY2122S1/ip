package duke.command;

import duke.Storage;
import duke.DukeException;
import duke.TaskList;
import duke.task.Task;

/**
 * Class that represents and defines the behaviour of the Add command
 */
public class AddTaskCommand extends Command{
    protected Task task;

    /**
     * Constructor initialises the Add task command object with the task to be added to the task list
     * @param task Task to add into task list
     */
    public AddTaskCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes Add task command and adds given task into the Task list
     * @param taskList Task Manager that executes task based on the given command
     * @param storage Stores User data in a csv file
     * @throws DukeException Throws any error messages from addTasks or saveData functions
     */
    @Override
    public void executeCommand(TaskList taskList, Storage storage) throws DukeException {
        taskList.addTasks(task, true);
        storage.saveData(taskList);
    }
}
