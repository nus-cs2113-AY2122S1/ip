package duke.command;

import duke.DukeException;
import duke.Output;
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
     * @param taskList the taskManager that the task will be added to
     * @param storage  the storage class that will store the updated TaskList
     * @param ui       the ui class instance that will print out the completion message
     * @throws DukeException if the maximum number of tasks has been reached
     */
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) throws DukeException {
        taskList.addTask(task);
        String outputMessage = Output.getAddTaskMessage(task, taskList.size());
        ui.printOutput(outputMessage);
        storage.saveData(taskList);
    }
}
