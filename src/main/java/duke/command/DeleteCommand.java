package duke.command;

import duke.DukeException;
import duke.Output;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class DeleteCommand extends Command {
    private int taskIndex;

    /**
     * Constructs a DeleteCommand to delete the task at the specified index
     *
     * @param taskIndex the index of the task in taskList to be deleted
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Calls the deleteTask method in taskList to delete the task at the specified index
     *
     * @param taskList the taskList that contains the task to be marked
     * @param storage  the storage class that will store the updated TaskList
     * @param ui       the ui class instance that will print out the completion message
     * @throws DukeException if taskIndex < 0 or if there is no task at the specified index
     */
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) throws DukeException {
        Task deletedTask = taskList.deleteTask(taskIndex);
        String outputMessage = Output.getDeleteTaskMessage(deletedTask, taskList.size());
        ui.printOutput(outputMessage);
        storage.saveData(taskList);
    }
}
